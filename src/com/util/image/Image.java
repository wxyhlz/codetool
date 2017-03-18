/*
 * 文 件 名:  image.java
 * 版    权:  changjet Co., Ltd. Copyright 2010-2011,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  张卓卫
 * 修改时间:  2011-4-22
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.util.image;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class Image extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void makeImage() throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		HttpServletResponse res = ServletActionContext.getResponse();
		BufferedImage img = new BufferedImage(68, 22,BufferedImage.TYPE_INT_RGB);

		// 得到该图片的绘图对象

		Graphics g = img.getGraphics();

		Random r = new Random();

		Color c = new Color(200, 150, 255);

		g.setColor(c);
		// 填充整个图片的颜色
		g.fillRect(0, 0, 68, 22);
		// 向图片中输出数字和字母

		StringBuffer sb = new StringBuffer();

		char[] ch = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

		int index, len = ch.length;

		for (int i = 0; i < 4; i++) {

			index = r.nextInt(len);

			g.setColor(new Color(r.nextInt(88), r.nextInt(188), r.nextInt(255)));
			// 输出的字体和大小
			g.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 22));
			// 写什么数字，在图片的什么位置画
			g.drawString("" + ch[index], (i * 15) + 3, 18);

			sb.append(ch[index]);

		}

		session.put("piccode", sb.toString());

		ImageIO.write(img, "JPG", res.getOutputStream()); 
		//PrintWriter   out=res.getWriter(); 
	}

}
