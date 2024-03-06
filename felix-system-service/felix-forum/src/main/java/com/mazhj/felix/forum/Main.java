package com.mazhj.felix.forum;

import com.mazhj.common.core.excel.PoiExcelDefinition;
import com.mazhj.common.core.exception.ExcelParseException;
import com.mazhj.felix.forum.websocket.container.SensitiveWordsTire;
import net.minidev.json.JSONObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws ExcelParseException, IOException {
//        PoiExcelDefinition excelDefinition = new PoiExcelDefinition(new File("/Users/mazhj/Desktop/毕设/sensitive_terms_1062.xlsx"));
//        Set<String> collect = excelDefinition.allValForStrToStream().collect(Collectors.toSet());
//        SensitiveWordsTire tire = new SensitiveWordsTire(collect);
//        System.out.println(tire.filter("你是真不知道自己是谁了，你个傻逼，曹你妈的，你什么东西，你身上淫水"));
        // 创建一个类型为TYPE_INT_ARGB的BufferedImage对象，这种类型支持透明度
        BufferedImage bufferedImage = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bufferedImage.createGraphics();

        // 设置透明度（alpha）值，范围是0.0到1.0，其中0.0完全透明，1.0完全不透明
        g2d.setComposite(AlphaComposite.SrcOver.derive(1.0f));

        // 设置画笔颜色和写入文字作为示例
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.setFont(new Font("Arial", Font.BOLD, 20));
        g2d.drawString("Watermark", 50, 100);

        // 释放资源
        g2d.dispose();

        // 保存生成的图片
        try {
            File file = new File("transparentImage.png");
            ImageIO.write(bufferedImage, "PNG", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
