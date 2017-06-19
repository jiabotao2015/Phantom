/**   
* @Project: Phantom-API
* @Title: Java.IO 流操作工具类
* @Package Phantom.API.Util
* @Files: StringUtil.java
* @Description: 
* @author jiabotao 
* @date 2017年2月22日 下午11:54:43
* @version V1.0   
*/
package Phantom.API.Util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class StringUtil {

	/**
	 * OutputStream 转 InputStream
	 * 
	 * @param pdfoutput
	 * @return
	 */
	public static ByteArrayInputStream parse(ByteArrayOutputStream output) {
		//  Auto-generated method stub
		/*ByteArrayOutputStream   baos=new   ByteArrayOutputStream();
        baos=(ByteArrayOutputStream) out;
        ByteArrayInputStream swapStream = new ByteArrayInputStream(baos.toByteArray());
        return swapStream;*/
		ByteArrayInputStream swapStream = new ByteArrayInputStream(output.toByteArray());
		return swapStream;
	}

	/**
	 * InputStream 转 OutputStream
	 * @param input
	 * @return
	 * @throws Exception
	 */
	public static ByteArrayOutputStream parse(InputStream input) throws Exception {
		ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
		int ch;
		while ((ch = input.read()) != -1) {
			swapStream.write(ch);
		}
		return swapStream;
	}

	/**
	 * inputStream转String
	 * @param input
	 * @return
	 * @throws Exception
	 */
	public static String parse_String(InputStream input) throws Exception {
		ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
		int ch;
		while ((ch = input.read()) != -1) {
			swapStream.write(ch);
		}
		return swapStream.toString();
	}

	/**
	 * OutputStream 转String
	 * @param output
	 * @return
	 * @throws Exception
	 */
	public static String parse_String(OutputStream output) throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		baos = (ByteArrayOutputStream) output;
		ByteArrayInputStream swapStream = new ByteArrayInputStream(baos.toByteArray());
		return swapStream.toString();
	}

	/**
	 * String转inputStream
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static ByteArrayInputStream parse_inputStream(String str) throws Exception {
		ByteArrayInputStream input = new ByteArrayInputStream(str.getBytes());
		return input;
	}

	/**
	 * String 转outputStream
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static ByteArrayOutputStream parse_outputStream(String str) throws Exception {
		return parse(parse_inputStream(str));
	}

}
