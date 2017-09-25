package com.verysu.mydata.core;

import com.google.common.base.Preconditions;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author Cocodroid
 * @create 2017-07-18 15:25
 */
public abstract class AbstractCopy implements CopyInterface{

    private final Log log = LogFactory.getLog(AbstractCopy.class);

    private volatile boolean stop = false;
    private SourceDataInterface sourceDataInterface;
    private DestDataInterface destDataInterface;

    public SourceDataInterface getSourceDataInterface() {
        return sourceDataInterface;
    }

    public void setSourceDataInterface(SourceDataInterface sourceDataInterface) {
        this.sourceDataInterface = sourceDataInterface;
    }

    public DestDataInterface getDestDataInterface() {
        return destDataInterface;
    }

    public void setDestDataInterface(DestDataInterface destDataInterface) {
        this.destDataInterface = destDataInterface;
    }

    public AbstractCopy(SourceDataInterface sourceDataInterface, DestDataInterface destDataInterface){
        this.sourceDataInterface = sourceDataInterface;
        this.destDataInterface = destDataInterface;
    }

    public AbstractCopy() {

    }

    @Override
    public void copy() {
        Preconditions.checkNotNull(sourceDataInterface, "SourceData must not null!");
        Preconditions.checkNotNull(destDataInterface, "DestData must not null!");
        log.info("--------begin copy--------");
        long copyBegin = System.currentTimeMillis();
        int copyCount = 0;
        int i = 0;
        try {
            int offset = 0;
            while (!stop) {
                i++;
                log.info("-------- copy No."+i+"--------");
                long selectBegin = System.currentTimeMillis();
                List datas = sourceDataInterface.select(offset);
                if(datas == null || datas.size() <=0){
                    break;//end
                }
                long selectEnd = System.currentTimeMillis();
                log.info("--------select time(ms):" + (selectEnd - selectBegin));

                long saveBegin = System.currentTimeMillis();
                destDataInterface.save(datas);
                long saveEnd = System.currentTimeMillis();
                log.info("--------save time:" + (saveEnd - saveBegin));
                long count = datas != null ? datas.size() : 0;
                copyCount += count;
                datas = null;

                long delBegin = System.currentTimeMillis();
                sourceDataInterface.delete(null);
                long delEnd = System.currentTimeMillis();
                log.info("--------delete time:" + (delEnd - delBegin));

                log.info("--------offset:" + offset + ",count:" + count);
                log.info("--------total time:" + (saveEnd - selectBegin) + ",count:" + count);
                offset += count;
            }
            offset = 0;
        } catch (DataException e) {
            e.printStackTrace();
            log.error("Copy operation DataException occurs!",e);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            long copyEnd = System.currentTimeMillis();
            log.info("--------copy total time:" + (copyEnd - copyBegin) + ",count:" + copyCount);
        }
        log.info("--------end copy--------");
    }

    public void stop(){
        this.stop = true;
    }
}
