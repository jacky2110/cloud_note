package cn.tedu.cloud_note.dao;

import java.util.List;
import java.util.Map;

import cn.tedu.cloud_note.entity.Share;

public interface ShareDao {
        // ����һ�����������ڷ���ʼǣ���cn_share���в���һ�����ݣ�
        public int shareNote(Share share);
        
        // ����һ������������ģ������
        public List<Share> findLikeTitle(SelectLimit sl);
        
        // ����һ������������ģ������
        public List<Share> findLikeTitleMap(Map<String, Object> map);
}
