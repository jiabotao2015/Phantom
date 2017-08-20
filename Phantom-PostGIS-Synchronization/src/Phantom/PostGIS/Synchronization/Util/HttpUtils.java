package Phantom.PostGIS.Synchronization.Util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.imageio.ImageIO;

/**
 * HTTP POST和GET处理工具类
 * 
 * @author jiang.li
 * @date 2013-12-18 11:22
 */
public class HttpUtils {

	/**
	 * 向指定URL发送GET方法的请求
	 * 
	 * @param url
	 *            发送请求的URL
	 * @param param
	 *            请求参数
	 * @return URL 所代表远程资源的响应结果
	 */
	public static InputStream sendGet(String url, HashMap<String, String> params) {
		String result = "";
		BufferedReader in = null;
		InputStream is =null;
		try {
			/** 组装参数 **/
		//	String param = parseParams(params);
			//String urlNameString = url + "?" + param;
			URL realUrl = new URL(url);
			/** 打开和URL之间的连接 **/
			URLConnection connection = realUrl.openConnection();
			/** 设置通用的请求属性 **/
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			/** 建立实际的连接 **/
			connection.connect();
			is = connection.getInputStream();
			/** 定义 BufferedReader输入流来读取URL的响应 **/
			//in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
			//BufferedImage image = ImageIO.read(connection.getInputStream());  
			//Image big = image.getScaledInstance(256, 256,Image.SCALE_DEFAULT);
			//BufferedImage inputbig = new BufferedImage(256, 256,BufferedImage.TYPE_INT_BGR);
	        //inputbig.getGraphics().drawImage(image, 0, 0, 256, 256, null); //画图
	        //String name = x+"-"+y+"-"+z+ ".png";
	        //ImageIO.write(inputbig, "png", new File("D:/MapBox/"+name)); 
			
			//String line;
			//while ((line = in.readLine()) != null) {
			//	result += line;
			//}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {/** 使用finally块来关闭输入流 **/
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return is;
	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 * 
	 * @param url
	 *            发送请求的 URL
	 * @param param
	 *            请求参数
	 * @return 所代表远程资源的响应结果
	 */
	public static String sendPost(String url, HashMap<String, String> params) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			/** 打开和URL之间的连接 **/
			URLConnection conn = realUrl.openConnection();
			/** 设置通用的请求属性 **/
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			/** 发送POST请求必须设置如下两行 **/
			conn.setDoOutput(true);
			conn.setDoInput(true);
			/** 获取URLConnection对象对应的输出流 **/
			out = new PrintWriter(conn.getOutputStream());
			/** 发送请求参数 **/
			String param = parseParams(params);
			out.print(param);
			/** flush输出流的缓冲 **/
			out.flush();
			/** 定义BufferedReader输入流来读取URL的响应 **/
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally { /** 使用finally块来关闭输出流、输入流 **/
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 将HashMap参数组装成字符串
	 * 
	 * @param map
	 * @return
	 */
	private static String parseParams(HashMap<String, String> map) {
		StringBuffer sb = new StringBuffer();
		if (map != null) {
			for (Entry<String, String> e : map.entrySet()) {
				sb.append(e.getKey());
				sb.append("=");
				sb.append("&");
			}
			sb.substring(0, sb.length() - 1);
		}
		return sb.toString();
	}

	
}


