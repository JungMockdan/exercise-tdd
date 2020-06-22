package test.java.chap08.testable;

import org.junit.jupiter.api.Test;
import test.java.chap08.payinfo.PayInfo;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PaySyncTest {

    // 대역 생성
    private MemoryPayInfoDao memoryDao = new MemoryPayInfoDao();

    @Test
    void allDataSaved() throws IOException {
        PaySync paySync = new PaySync();
        paySync.setPayInfoDao(memoryDao);
        paySync.setFilePath("src/test/resources/c0111.csv");

        paySync.sync();

        List<PayInfo> savedInfos = memoryDao.getAll();
        assertEquals(2, savedInfos.size());
    }
}