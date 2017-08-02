package shop.haj.repository;

import com.mysql.jdbc.Statement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import shop.haj.entity.Notice;
import shop.haj.entity.Pagination;
import shop.haj.repository.RowMapper.NoticeRowMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * <p>Title: shop.ha.repository</p>
 * <p/>
 * <p>
 * Description: TODO
 * </p>
 * <p/>
 *
 * @author hao
 *         CreateTime：3/4/17
 */
@Repository
public class NoticeRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 查找某家店铺的全部公告
	 * @param shop_id
	 * @return
	 */
	public List<Notice> findAllNotice(int shop_id, Pagination page){
		return null;
	}
	
	/**
	 * 查找该店铺全部需要显示的公告
	 * @param shop_id
	 * @return
	 */
	public List<Notice> findAllShowedNotice(int shop_id, Pagination page){
		String sql = "select id, shop_id, content, is_show, " +
				" date_format(create_time, '%Y-%m-%d %H:%i:%s') create_time, " +
				" date_format(create_time, '%Y-%m-%d %H:%i:%s') create_time  " +
				" from notice where shop_id=? and is_show=1 ";// + page.getOrderByLimitString();
		
		return jdbcTemplate.query(sql, new Object[]{shop_id},
				new NoticeRowMapper());
		
	}
	
	/**
	 * 新增一条公告
	 * @param notice
	 * @return
	 */
	public Notice addNotice(Notice notice){
		return null;
	}
	
	/**
	 * 删除店家的公告
	 * @param shop_id
	 * @param notice_id
	 * @return
	 */
	public int deleteNotice(int shop_id, int notice_id){
		String sql = "delete from notice where id=? and shop_id=?";
		
		int result = jdbcTemplate.update(sql, new Object[]{notice_id, shop_id});
		if(result <= 0)
			return 0;
		return 1;
	}
	
}