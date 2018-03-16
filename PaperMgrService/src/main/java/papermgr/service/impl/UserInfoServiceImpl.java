package papermgr.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

import papermgr.base.dao.UserinfoMapper;
import papermgr.base.model.Userinfo;
import papermgr.common.RandomNum;
import papermgr.service.UserInfoService;
import papermgr.uimodel.EUIPageList;

@Service
public class UserInfoServiceImpl implements UserInfoService {

	@Resource
	private UserinfoMapper userinfoMapper;

	@Override
	public Userinfo login(String username, String password, StringBuilder stringBuilder) {
		Userinfo userinfo = new Userinfo();
		userinfo.setUsername(username);
		List<Userinfo> list = userinfoMapper.getList(userinfo);
		if (list == null || list.size() < 1) {
			stringBuilder.append("该用户不存在");
			return null;
		}
		if (!list.get(0).getPassword().equals(password)) {
			stringBuilder.append("用户名或密码错误");
			return null;
		}
		stringBuilder.append("登陆成功");
		return list.get(0);
	}

	@Override
	public Userinfo single(String userinfoid) {
		return userinfoMapper.getSingle(userinfoid);
	}

	@Override
	public EUIPageList<Userinfo> list(Userinfo userinfo, int page, int rows) {
		PageList<Userinfo> pageList = userinfoMapper.getListWithPage(userinfo, new PageBounds(page, rows));
		return new EUIPageList<Userinfo>(pageList.getPaginator().getTotalCount(), pageList);
	}

	@Override
	public void add(Userinfo userinfo) throws Exception {
		// 判断用户名是否存在
		Userinfo condition = new Userinfo();
		condition.setUsername(userinfo.getUsername());
		if (userinfoMapper.count(condition) > 0) {
			throw new Exception("用户名已经存在");
		}
		userinfo.setUserid(RandomNum.getAID());
		userinfo.setCreatetime(new Date());
		userinfoMapper.insert(userinfo);
	}

	@Override
	public void update(Userinfo userinfo) {
		userinfoMapper.update(userinfo);
	}

	@Override
	public void batchDelete(List<String> ids) {
		userinfoMapper.batchDelete(ids);
	}

}
