package com.chen.fastdfs;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.ProtoCommon;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Instant;

@SpringBootTest
class DatawayApplicationTests {

	@Test
	void contextLoads() throws IOException, MyException {
		ClientGlobal.initByProperties("fastdfs-client.properties");
		TrackerClient trackerClient = new TrackerClient();
		TrackerServer trackerServer = trackerClient.getConnection();
		StorageServer storageServer = null;
		StorageClient1 client1 = new StorageClient1(trackerServer, storageServer);
		NameValuePair pair[] = null;
		String fileId = client1.upload_file1("C:\\Users\\26612\\Pictures\\壁纸\\1.jpg", "jpg", pair);
		System.out.println(fileId);
	}

	@Test
	void testDownload() {
		try {
			ClientGlobal.initByProperties("fastdfs-client.properties");
			TrackerClient tracker = new TrackerClient();
			TrackerServer trackerServer = tracker.getConnection();
			StorageServer storageServer = null;
			StorageClient1 client = new StorageClient1(trackerServer, storageServer);
			byte[] bytes = client.download_file1("group1/M00/00/00/wKgB8166MtCAe7UCAAVo9EWUTMw117.jpg");
			FileOutputStream fos = new FileOutputStream(new File("C:\\Users\\26612\\Pictures\\壁纸\\1-2.png"));
			fos.write(bytes);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void getToken() throws Exception {
		int ts = (int) Instant.now().getEpochSecond();
		String token = ProtoCommon.getToken("M00/00/00/wKgB8166bSiAclZ5AAVo9EWUTMw636.jpg", ts, "FastDFS1234567890");
		StringBuilder sb = new StringBuilder();
		sb.append("http://192.168.1.243/");
		sb.append("group1/M00/00/00/wKgB8166bSiAclZ5AAVo9EWUTMw636.jpg");
		sb.append("?token=").append(token);
		sb.append("&ts=").append(ts);
		System.out.println(sb.toString());
	}

}
