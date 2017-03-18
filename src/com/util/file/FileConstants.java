package com.util.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;


public class FileConstants {

	/**
	 * 移动文件
	 * @param sourceFile
	 * @param targetFile
	 * @throws IOException
	 */
    public static void moveFile(String srcPathFile, String destPathFile) throws IOException {
    	File sourceFile = new File(srcPathFile);
    	File targetFile = new File(destPathFile);
    	
    	Integer ipos = destPathFile.indexOf("account");    	
    	String dir = destPathFile.substring(0,ipos+8);
    	File filedir = new File(dir);
    	if(!filedir.exists()){
    		filedir.mkdirs();
    	}
        BufferedInputStream inBuff = null;
        BufferedOutputStream outBuff = null;
        try {
            // 新建文件输入流并对它进行缓冲
            inBuff = new BufferedInputStream(new FileInputStream(sourceFile));

            // 新建文件输出流并对它进行缓冲
            outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));

            // 缓冲数组
            byte[] b = new byte[1024 * 5];
            int len;
            while ((len = inBuff.read(b)) != -1) {
                outBuff.write(b, 0, len);
            }
            // 刷新此缓冲的输出流
            outBuff.flush();
        } finally {
            // 关闭流
            if (inBuff != null)
                inBuff.close();
            if (outBuff != null)
                outBuff.close();
        }
        //删除原文件 
        sourceFile.delete(); 
    }
    
	/**
	 * 复制文件
	 * @param sourceFile
	 * @param targetFile
	 * @throws IOException
	 */
    public static void copyFile(File sourceFile, File targetFile) throws IOException {
        BufferedInputStream inBuff = null;
        BufferedOutputStream outBuff = null;
        try {
            // 新建文件输入流并对它进行缓冲
            inBuff = new BufferedInputStream(new FileInputStream(sourceFile));

            // 新建文件输出流并对它进行缓冲
            outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));

            // 缓冲数组
            byte[] b = new byte[1024 * 5];
            int len;
            while ((len = inBuff.read(b)) != -1) {
                outBuff.write(b, 0, len);
            }
            // 刷新此缓冲的输出流
            outBuff.flush();
        } finally {
            // 关闭流
            if (inBuff != null)
                inBuff.close();
            if (outBuff != null)
                outBuff.close();
        }
    }
    
    /**
     * 复制文件夹
     * @param sourceDir
     * @param targetDir
     * @throws IOException
     */
    public static void copyDirectiory(String sourceDir, String targetDir) throws IOException {
        // 新建目标目录
        (new File(targetDir)).mkdirs();
        // 获取源文件夹当前下的文件或目录
        File[] file = (new File(sourceDir)).listFiles();
        for (int i = 0; i < file.length; i++) {
            if (file[i].isFile()) {
                // 源文件
                File sourceFile = file[i];
                // 目标文件
                File targetFile = new File(new File(targetDir).getAbsolutePath() + File.separator + file[i].getName());
                copyFile(sourceFile, targetFile);
            }
            if (file[i].isDirectory()) {
                // 准备复制的源文件夹
                String dir1 = sourceDir + "/" + file[i].getName();
                // 准备复制的目标文件夹
                String dir2 = targetDir + "/" + file[i].getName();
                copyDirectiory(dir1, dir2);
            }
        }
    }

    /**
     * 把文件转换为GBK文件
     * @param srcFileName
     * @param destFileName
     * @param srcCoding
     * @param destCoding
     * @throws IOException
     */
    public static void copyFile(File srcFileName, File destFileName, String srcCoding, String destCoding) throws IOException {
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(srcFileName), srcCoding));
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(destFileName), destCoding));
            char[] cbuf = new char[1024 * 5];
            int len = cbuf.length;
            int off = 0;
            int ret = 0;
            while ((ret = br.read(cbuf, off, len)) > 0) {
                off += ret;
                len -= ret;
            }
            bw.write(cbuf, 0, off);
            bw.flush();
        } finally {
            if (br != null)
                br.close();
            if (bw != null)
                bw.close();
        }
    }

    /**
     * 删除目录下的所有文件
     * @param filepath
     * @throws IOException
     */
    public static void deldir(String filepath) throws IOException {
        File f = new File(filepath);// 定义文件路径
        if (f.exists() && f.isDirectory()) {// 判断是文件还是目录
            if (f.listFiles().length == 0) {// 若目录下没有文件则直接删除
                f.delete();
            } else {// 若有则把文件放进数组，并判断是否有下级目录
                File delFile[] = f.listFiles();
                int i = f.listFiles().length;
                for (int j = 0; j < i; j++) {
                    if (delFile[j].isDirectory()) {
                    	deldir(delFile[j].getAbsolutePath());// 递归调用del方法并取得子目录路径
                    }
                    delFile[j].delete();// 删除文件
                }
            }
        }
    }
    
    public static void readFileByCharset(String fileName,String charsetName){
		try{
			//读取内容
			char[] charInData = new char[500];
			int length = 0;
			String acontent = "";			  
			InputStreamReader isr = new InputStreamReader(new FileInputStream(fileName), charsetName);
			while ((length = isr.read(charInData)) != -1) {
				acontent = acontent + String.valueOf(charInData, 0, length);			
			}
			 
			//清除注释
			int beginnote,endnote;
			while((beginnote = acontent.indexOf("<!--"))>0){
				endnote = acontent.indexOf("-->");
				acontent = acontent.substring(0, beginnote)+acontent.substring(endnote+3, acontent.length());
				//System.out.println(acontent);
				
			}
			//修改img路径
			//Date date1 = new Date(); 
			//System.out.println(df.format(date1));
			acontent = acontent.replaceAll("<!\\[if !vml\\]>", " "); 
			acontent = acontent.replaceAll("<!\\[endif\\]>", " "); 

			//acontent = acontent.replaceAll("aaa.files/", ""); 
			
			try {
				//BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("C:/test/aaa3.html"),"gb2312"));
				bw.write(acontent);
				bw.flush();
				bw.close();			
			} catch (Exception e) {
				e.printStackTrace();
			}
			//System.out.println(acontent);
		}catch(Exception e){
			e.printStackTrace();
		}
	} 
    
    /**
     * 删除目录下的文件
     * @param filepath
     * @throws IOException
     */
    public static void delfile(String filepath) throws IOException {
        File f = new File(filepath);// 定义文件路径
        if (f.exists()) {// 判断是文件还是目录
           f.delete();
        }
    }
    
    /**
     * 创建文件目录
     * @param filepath
     * @throws IOException
     */
    public static void mkdirs(String filepath) {
		File file = new File(filepath);
		if(!file.exists()) file.mkdirs();
	}
    
    /**
     * 
     * @param sourceDirPath
     * @param targetDirPath
     * @throws IOException
     */
    /*public static void copyDir(String sourceDirPath, String targetDirPath) throws IOException {
        // 创建目标文件夹
        (new File(targetDirPath)).mkdirs();
        // 获取源文件夹当前下的文件或目录
        File[] file = (new File(sourceDirPath)).listFiles();
        for (int i = 0; i < file.length; i++) {
            if (file[i].isFile()) {
                // 复制文件
                String type = file[i].getName().substring(file[i].getName().lastIndexOf(".") + 1);

                if (type.equalsIgnoreCase("txt"))
                    copyFile(file[i], new File(targetDirPath + file[i].getName()), MTOServerConstants.CODE_UTF_8,
                            MTOServerConstants.CODE_GBK);
                else
                    copyFile(file[i], new File(targetDirPath + file[i].getName()));
            }
            if (file[i].isDirectory()) {
                // 复制目录
                String sourceDir = sourceDirPath + File.separator + file[i].getName();
                String targetDir = targetDirPath + File.separator + file[i].getName();
                FileUtil.copyDirectiory(sourceDir, targetDir);
            }
        }
    }*/
    
    /**
     * 读取文件中内容
     * 
     * @param path
     * @return
     * @throws IOException
     */
   /* public static String readFileToString(String path) throws IOException {
        String resultStr = null;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(path);
            byte[] inBuf = new byte[2000];
            int len = inBuf.length;
            int off = 0;
            int ret = 0;
            while ((ret = fis.read(inBuf, off, len)) > 0) {
                off += ret;
                len -= ret;
            }
           // resultStr = new String(new String(inBuf, 0, off, MTOServerConstants.CODE_GBK).getBytes());
        } finally {
            if (fis != null)
                fis.close();
        }
        return resultStr;
    }*/

    /**
     * 文件转成字节数组
     * 
     * @param path
     * @return
     * @throws IOException
     */
    public static byte[] readFileToBytes(String path) throws IOException {
        byte[] b = null;
        InputStream is = null;
        File f = new File(path);
        try {
            is = new FileInputStream(f);
            b = new byte[(int) f.length()];
            is.read(b);
        } finally {
            if (is != null)
                is.close();
        }
        return b;
    }

    /**
     * 将byte写入文件中
     * 
     * @param fileByte
     * @param filePath
     * @throws IOException
     */
    public static void byteToFile(byte[] fileByte, String filePath) throws IOException {
        OutputStream os = null;
        try {
            os = new FileOutputStream(new File(filePath));
            os.write(fileByte);
            os.flush();
        } finally {
            if (os != null)
                os.close();
        }
    }

    /**
     * 将目录文件打包成zip
     * 
     * @param srcPathName
     * @param zipFilePath
     * @return 成功打包true 失败false
     */
   /* public static boolean compress(String srcPathName, String zipFilePath) {
        if (strIsNull(srcPathName) || strIsNull(zipFilePath))
            return false;

        File zipFile = new File(zipFilePath);
        File srcdir = new File(srcPathName);
        if (!srcdir.exists())
            return false;
        
        Project prj = new Project();
        Zip zip = new Zip();
        zip.setProject(prj);
        zip.setDestFile(zipFile);
        
        FileSet fileSet = new FileSet();
        fileSet.setProject(prj);
        fileSet.setDir(srcdir);
        
        zip.addFileset(fileSet);
        zip.execute();
        return zipFile.exists();
    }*/

    /**
     * 判空字串
     * 
     * @param str
     * @return 为空true
     */
    public static boolean strIsNull(String str) {
        return str == null || str.equals("");
    }

    /**
     * 折分数组
     * 
     * @param ary
     * @param subSize
     * @return
     */
    public static List<List<Object>> splitAry(Object[] ary, int subSize) {
        int count = ary.length % subSize == 0 ? ary.length / subSize : ary.length / subSize + 1;

        List<List<Object>> subAryList = new ArrayList<List<Object>>();

        for (int i = 0; i < count; i++) {
            int index = i * subSize;

            List<Object> list = new ArrayList<Object>();
            int j = 0;
            while (j < subSize && index < ary.length) {
                list.add(ary[index++]);
                j++;
            }

            subAryList.add(list);
        }

        return subAryList;
    }

    /**
     * 数组转成字符串
     * @param mobile
     * @return
     */
    public static String ArrayToString(Object[] mobile) {
        String destId = "";
        for (Object phone : mobile) {
            destId += " " + (String) phone;
        }
        return destId.trim();
    }

    /**
     * 生成文件
     * @param filePath
     * @param fileName
     * @param fileContent
     */
    public static void writeFile(String filePath, String fileName, String fileContent) {
		
		/**
		 * 创建文件路径
		 */
		File file = new File(filePath);
		if(!file.isDirectory()){
			file.mkdirs();
		}
		
		String pathfile = filePath+"\\"+fileName;
		//生成文件
	 
		try {
			//BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(pathfile),"UTF-8"));
			bw.write(fileContent.toCharArray());
			bw.flush();
			bw.close();			
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
	}




}
