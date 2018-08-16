package com.wuxi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.junit.Test;

import com.jcraft.jsch.ChannelSftp;
import com.panda.util.SFTPUtils;

public class SFTPReadTest {

	@Test
	public void readData(){
		String userName = "recon";
		String password = "93>D6UH]B;:~";
		String server = "122.224.137.178";
		int port = 40020;
		String fileDir = "/recon/mer/xd/";
		String fileName = "ct_remit_20180809.txt";
		ChannelSftp channelSftp = null;
		BufferedReader reader = null;
		try {
			channelSftp = SFTPUtils.getConnection(server, port, userName, password);
			channelSftp.cd(fileDir);
			InputStream inputStream = null;
			try {
				//拿到输入流
				inputStream = channelSftp.get(fileName);
				//拿到输出流
				//outputStream = channelSftp.put(fileName);
			} catch (Exception e) {
				System.out.println("未找到文件");
				e.printStackTrace();
			}
			reader = new BufferedReader(new InputStreamReader(inputStream));
			String string = null;
			while((string = reader.readLine()) != null){
				System.out.println(string);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(reader != null){
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			SFTPUtils.disconnected(channelSftp);
		}
	}
}
