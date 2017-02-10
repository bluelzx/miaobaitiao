package cn.hjf.bixia.business;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import cn.hjf.bixia.businessmodel.ConsumeRecord;
import cn.hjf.bixia.businessmodel.DaoModelTransfer;
import cn.hjf.bixia.businessmodel.QueryInfo;
import cn.hjf.bixia.daomodel.BusinessModelTransfer;
import cn.hjf.bixia.daomodel.ConsumeRecordModel;
import cn.hjf.bixia.db.ConsumeRecordDaoSqliteImpl;
import cn.hjf.bixia.db.ConsumeTypeDaoSqliteImpl;
import cn.hjf.bixia.db.IConsumeRecordDao;
import cn.hjf.bixia.db.IConsumeTypeDao;

/**
 * 消费记录管理的业务逻辑，负责添加和修改消费记录
 * @author huangjinfu
 *
 */ 
public class ConsumeRecordManagerBusiness {

    private IConsumeRecordDao mConsumeRecordDao;
    private IConsumeTypeDao mConsumeTypeDao;
    private Context mContext;
    private BusinessModelTransfer mBusinessModelTransfer;
    private DaoModelTransfer mDaoModelTransfer;
    
    public ConsumeRecordManagerBusiness(Context context) {
        this.mContext = context;
        mConsumeRecordDao = new ConsumeRecordDaoSqliteImpl(mContext);
        mConsumeTypeDao = new ConsumeTypeDaoSqliteImpl(mContext);
        mBusinessModelTransfer = new BusinessModelTransfer(mContext);
        mDaoModelTransfer=  new DaoModelTransfer();
        init();
    }
    
    /**
     * 如果表不存在，创建表
     */
    public void init() {
        if (!mConsumeRecordDao.isTableExist()) {
            mConsumeRecordDao.createTable();
        }
    }
    
    /**
     * 添加新的消费记录
     * @param record
     */
    public boolean addRecord(ConsumeRecord record) {
        return mConsumeRecordDao.insert(mDaoModelTransfer.getConsumeRecordModel(record));
    }
    
    /**
     * 删除消费记录
     * @param record
     */
    public boolean deleteRecord(ConsumeRecord consumeRecord) {
        return mConsumeRecordDao.delete(consumeRecord.getId());
    }
    
    /**
     * 修改现有的消费记录
     * @param record
     */
    public boolean modifyRecord(ConsumeRecord record) {
        return mConsumeRecordDao.update(mDaoModelTransfer.getConsumeRecordModel(record));
    }
    
    /**
     * 查询消费记录
     * @param queryInfo
     * @return
     */
    public List<ConsumeRecord> queryRecords(QueryInfo queryInfo) {
        return getBusinessModels(mConsumeRecordDao.queryRecords(mDaoModelTransfer.getQueryInfoModel(queryInfo)));
    }
    
    
    /**
     * 把多个数据层模型转换为业务逻辑模型
     * @param consumeRecordModel
     * @return
     */
    private List<ConsumeRecord> getBusinessModels(List<ConsumeRecordModel> consumeRecordModels) {
        List<ConsumeRecord> consumeRecords = new ArrayList<ConsumeRecord>();
        for (ConsumeRecordModel consumeRecordModel : consumeRecordModels) {
            consumeRecords.add(mBusinessModelTransfer.getConsumeRecord(consumeRecordModel));
        }
        return consumeRecords;
    }
    
    /**
     * 把多个业务逻辑模型转换为数据层模型
     * @param consumeRecordModel
     * @return
     */
    private List<ConsumeRecordModel> getDaoModels(List<ConsumeRecord> consumeRecords) {
        List<ConsumeRecordModel> consumeRecordModels = new ArrayList<ConsumeRecordModel>();
        for (ConsumeRecord consumeRecord : consumeRecords) {
            consumeRecordModels.add(mDaoModelTransfer.getConsumeRecordModel(consumeRecord));
        }
        return consumeRecordModels;
    }
}
