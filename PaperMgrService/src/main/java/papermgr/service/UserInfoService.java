package papermgr.service;

import java.util.List;

import papermgr.base.model.Userinfo;
import papermgr.uimodel.EUIPageList;

public interface UserInfoService {

	Userinfo login(String username, String password, StringBuilder stringBuilder);

	Userinfo single(String userinfoid);

	EUIPageList<Userinfo> list(Userinfo userinfo, int page, int rows);

	void add(Userinfo userinfo) throws Exception;

	void update(Userinfo userinfo);

	void batchDelete(List<String> ids);

}
