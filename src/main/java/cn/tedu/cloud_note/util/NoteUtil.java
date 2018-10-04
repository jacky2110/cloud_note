package cn.tedu.cloud_note.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;

public class NoteUtil {
        public static String md5(String src){
                try {
                        //��ȡMD5����
                        MessageDigest md = MessageDigest.getInstance("MD5");
                        //���ܴ���
                        byte[] output = md.digest(src.getBytes());
                        //����Base64ת�����ַ������
                        String str = Base64.encodeBase64String(output);
                        return str;
                } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                        throw new RuntimeException("����ʧ��",e);
                }
        }
        
        /**
         * ����UUID
         * java�е�UUIDΪ36λ���ظ��ַ���(32λ+4��"-"��)
         * ���ݿ���Ҳ��UUIDΪ362λ���ظ��ַ���
         * һ����������
         */
        public static String createId(){
                UUID uuid = UUID.randomUUID();
                return uuid.toString();
        }
        
        //����
        public static void main(String[] args) {
                System.out.println("����123456���ܺ�:"+md5("123456"));
                System.out.println(md5("123456").length());
                System.out.println("�Զ�����UUID����:"+createId());
                System.out.println(createId().length());
                
        }
}
