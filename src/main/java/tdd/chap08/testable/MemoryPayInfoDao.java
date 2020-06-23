package main.java.tdd.chap08.testable;

import main.java.tdd.chap08.payinfo.PayInfo;
import main.java.tdd.chap08.payinfo.PayInfoDao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class MemoryPayInfoDao extends PayInfoDao {
    private LinkedHashMap<String, PayInfo> infos = new LinkedHashMap<>();
    @Override
    public void insert(PayInfo pi) {
        infos.put(pi.getTrNum(), pi);
    }

    public List<PayInfo> getAll() {
        return new ArrayList<>(infos.values());
    }
}
