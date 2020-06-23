package main.java.tdd.chap08.nontestable;

import main.java.tdd.chap08.payinfo.PayInfo;
import main.java.tdd.chap08.payinfo.PayInfoDao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/*테스트하기 어려운 사례를 테스트 가능하게 바꾸어 보는 실습
 *
 * 시나리오 : 결제 대행업체가 결제 내역이 유효한지 확인할 수 있도록 익일 오전에 결제결과를 파일로 제공.
 * 이 파일을 읽어와 DB에 결제 내역을 반영하는 코드를 작성.
 * 1. 파일 경로가 하드코딩되어 있는 대상.
 * 2. 의존대상의 직접생성
 *  - " private PayInfoDao payInfoDao = new PayInfoDao();" 이부분
 *  _ PayInfoDao가 올바르게 동작하는 모든 환경에 대한 구성이 필요해지기 때문에 test가 어렵다.
 * */
public class PaySync {
    private PayInfoDao payInfoDao = new PayInfoDao();

    public void sync() throws IOException {
        Path path = Paths.get("D:\\data\\pay\\cp001.csv");
        List<PayInfo> payInfos = Files.lines(path).map(line -> {
            String[] data = line.split(",");
            PayInfo payInfo = new PayInfo(data[0], data[1], Integer.parseInt(data[2]));
            return payInfo;
        })
                .collect(Collectors.toList());
        payInfos.forEach(pi -> payInfoDao.insert(pi) );

    }

}
