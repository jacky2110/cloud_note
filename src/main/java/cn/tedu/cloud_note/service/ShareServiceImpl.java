package cn.tedu.cloud_note.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.tedu.cloud_note.dao.NoteDao;
import cn.tedu.cloud_note.dao.SelectLimit;
import cn.tedu.cloud_note.dao.ShareDao;
import cn.tedu.cloud_note.entity.Note;
import cn.tedu.cloud_note.entity.Share;
import cn.tedu.cloud_note.util.NoteResult;
import cn.tedu.cloud_note.util.NoteUtil;

//��spring�������ļ���ʹ����<context:component-scan>���������ɨ���
//����Ķ���ǰ��� @Controller @Component @Repository @Service��4��ע��֮һ��
//�����Խ����ཻ��spring��������Ҳ����˵������Ҫ�������ļ���spring-mvc.xml����Ϊ�÷�������beanԪ����
//4��ע��Ĺ�����ȥһ��������ͬ�����ƿ�����������ʹ�ø�ע��������;
//����������ҵ����ʵ���࣬���Ӧ��ʹ��@Service   
@Service("id_ShareServiceImpl")    // �����ָ��id����Ĭ�ϵ�idΪ����ĸСд������
public class ShareServiceImpl implements ShareService {

        // ʹ��ע�ͽ�������ע��
        @Resource(name="noteDao")
        private NoteDao noteDao;  // ���ڸ���noteId��cn_note���в�ѯ�ʼ���Ϣ
        
        // ʹ��ע�ͽ�������ע��
        @Resource(name="shareDao")
        private ShareDao shareDao;  // ���ڽ��鵽�ıʼ���Ϣ����cn_share����
        
        // ʹ��Springע�Ͷ�ȡ�����ļ��е�����
        @Value("#{id_Config.selectRows}")
        private Integer selectRows;
        
        public NoteResult<Share> shareNote(String noteId) {
                
                // ����Note����
                Note note = noteDao.findByNoteId(noteId);
                
                // ����Share��
                Share share = new Share();
                
                String shareId = NoteUtil.createId();
                String shareTitle = note.getCn_note_title();
                String shareBody = note.getCn_note_body();
                
                share.setCn_share_id(shareId);
                share.setCn_share_title(shareTitle);
                share.setCn_share_body(shareBody);
                share.setCn_note_id(noteId);
                
                // ��Share�����������ݿ�
                int row = shareDao.shareNote(share);
                
                // ���ܷ��ض���
                NoteResult<Share> result = new NoteResult<Share>();
                
                if (row == 1) {
                        result.setStatus(0);  // ����״̬Ϊ0����ʾ����ɹ�
                        result.setMsg("����ʼǳɹ�");  // ���÷�����Ϣ
                        result.setData(share);  // ����ѯ�����ӵ����ؽ����
                } else {
                        result.setStatus(1);  // ����״̬Ϊ1����ʾ����ʧ��
                        result.setMsg("����ʼ�ʧ��");  // ���÷�����Ϣ
                        result.setData(null);  // ����ѯ�����ӵ����ؽ����
                }
                
                return result;
        }

        public NoteResult<List<Share>> searchNote(String keyword, int page) {
                
                System.out.println("selectRows: " + selectRows);
                
                // ���ؼ��ʱ�Ϊģ������
                keyword = "%" + keyword + "%";
                // �����ѯ����ĵ�offset��rows
                Integer rows = selectRows;  // ÿ�β�ѯ2�м�¼
                Integer offset = (page - 1) * rows;
                
                
                SelectLimit sl = new SelectLimit();
                sl.setKeyword(keyword);
                sl.setOffset(offset);
                sl.setRows(rows);
                
                // �����ؼ���
                List<Share> list = shareDao.findLikeTitle(sl);
                
                // �������ؽ��
                NoteResult<List<Share>> result = new NoteResult<List<Share>>();
                result.setStatus(0);  // ����״̬Ϊ0����ʾ����ɹ�
                result.setMsg("�������");  // ���÷�����Ϣ
                result.setData(list);  // ����ѯ�����ӵ����ؽ����
                
                return result;
        }

        public NoteResult<List<Share>> searchNoteMap(String keyword, int page) {
                
                // ���ؼ��ʱ�Ϊģ������
                keyword = "%" + keyword + "%";
                // �����ѯ����ĵ�offset��rows
                Integer rows = selectRows;  // ÿ�β�ѯ2�м�¼
                Integer offset = (page - 1) * rows;
                
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("keyword", "%����%");
                map.put("offset", offset);
                map.put("rows", rows);
                
                // �����ؼ��ʣ�ע�⣺����ؼ��ʲ���%�����ʾҪ��ȫƥ��
                List<Share> list = shareDao.findLikeTitleMap(map);
                
                // �������ؽ��
                NoteResult<List<Share>> result = new NoteResult<List<Share>>();
                result.setStatus(0);  // ����״̬Ϊ0����ʾ����ɹ�
                result.setMsg("�������");  // ���÷�����Ϣ
                result.setData(list);  // ����ѯ�����ӵ����ؽ����
                
                return result;
        }
}
