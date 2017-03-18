package com.syscore.commons.listener;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.ehcache.Element;

import org.apache.log4j.Logger;

import com.syscore.constants.ConstantsWeb;
import com.syscore.system.sysdepart.dao.ISysdepartDao;
import com.syscore.system.sysdepart.model.SysdepartInfo;
 

public class LoadToCache {
	private static Logger logger = Logger.getLogger(LoadToCache.class.getName());
	private long startTime ;
	private long endTime;

	/**
	 * 
	 * 装载数据内容到eCache
	 * @see [类、类#方法、类#成员]
	 */ 
	public void loadCache() throws SQLException{
		logger.info("开始加载数据字典");
		startTime= System.currentTimeMillis();
		/**
		 * 部门
		 */	
		List<SysdepartInfo> departList = null;
		ISysdepartDao departDao  = (ISysdepartDao) ConstantsWeb.webApplicationContext.getBean("com.syscore.system.sysdepart.dao.ISysdepartDao");
		departList = departDao.selectEntitys("SysdepartInfo.selectByMap");
		
		ConstantsWeb.listCache.put(new Element("depart", departList));
		
		Map<String,SysdepartInfo> departMap = new HashMap<String,SysdepartInfo>();
		for(SysdepartInfo v : departList){
			departMap.put(v.getId(), v);
		}
		ConstantsWeb.mapCache.put(new Element("depart", departMap));
		
		//省份
		/*List<ProvinceInfo> provinceList = null;
		IProvinceDao provinceDao  = (IProvinceDao) Constants.webApplicationContext.getBean("com.polet.dictionary.dao.IProvinceDao");
		provinceList = provinceDao.selectEntitys("ProvinceInfo.selectList");
		
		Element eleProvinceList = new Element("province", provinceList);
		Constants.listCache.put(eleProvinceList);
		
		Map<String,ProvinceInfo> provinceMap = new HashMap<String,ProvinceInfo>();
		for(ProvinceInfo province : provinceList){
			provinceMap.put(String.valueOf(province.getId()), province);
		}
		Element eleProvinceMap = new Element("province", provinceMap);
		Constants.mapCache.put(eleProvinceMap);
		 
		
		//学校类型
		List<SchooltypeInfo> schtypeList = null;
		ISchooltypeDao schtypeDao  = (ISchooltypeDao) Constants.webApplicationContext.getBean("com.polet.dictionary.dao.ISchooltypeDao");
		schtypeList = schtypeDao.selectEntitys("SchooltypeInfo.selectList");
		
		Element eleSchtypeList = new Element("schtype", schtypeList);
		Constants.listCache.put(eleSchtypeList);
		
		Map<String,SchooltypeInfo> schtypeMap = new HashMap<String,SchooltypeInfo>();
		for(SchooltypeInfo schtype : schtypeList){
			schtypeMap.put(String.valueOf(schtype.getId()), schtype);
		}
		Element eleSchtypeMap = new Element("schtype", schtypeMap);
		Constants.mapCache.put(eleSchtypeMap);
		
		//科目
		List<SubjectInfo> subjectList = null;
		ISubjectDao subjectDao  = (ISubjectDao) Constants.webApplicationContext.getBean("com.polet.dictionary.dao.ISubjectDao");
		subjectList = subjectDao.selectEntitys("SubjectInfo.selectList");
		
		Element eleSubjectList = new Element("subject", subjectList);
		Constants.listCache.put(eleSubjectList);
		
		Map<String,SubjectInfo> subjectMap = new HashMap<String,SubjectInfo>();
		for(SubjectInfo subejct : subjectList){
			subjectMap.put(String.valueOf(subejct.getId()), subejct);
		}
		Element eleSubjectMap = new Element("subject", subjectMap);
		Constants.mapCache.put(eleSubjectMap);
		 
		//角色
		List<RoleInfo> roleList = null;
		IRoleDao roleDao  = (IRoleDao) Constants.webApplicationContext.getBean("com.frame.system.dao.IRoleDao");
		roleList = roleDao.selectEntitys("RoleInfo.selectList");
		
		Element eleRoleList = new Element("role", roleList);
		Constants.listCache.put(eleRoleList);
		
		Map<String,RoleInfo> roleMap = new HashMap<String,RoleInfo>();
		for(RoleInfo role : roleList){
			roleMap.put(String.valueOf(role.getId()), role);
		}
		Element eleRoleMap = new Element("role", roleMap);
		Constants.mapCache.put(eleRoleMap);*/
		
		//学校管理员
		/*List<UserInfo> userList = null;
		IUserDao userDao  = (IUserDao) Constants.webApplicationContext.getBean("com.polet.myaccount.dao.IUserDao");
		userList = userDao.selectEntitys("UserInfo.selectListByUsertype");
		
		Element eleUserList = new Element("schooladmin", userList);
		Constants.listCache.put(eleUserList);
		
		Map<String,UserInfo> userMap = new HashMap<String,UserInfo>();
		for(UserInfo user : userList){
			userMap.put(String.valueOf(user.getId()), user);
		}
		Element eleUserMap = new Element("schooladmin", userMap);
		Constants.mapCache.put(eleUserMap);
		*/
		
		//学校
		/*List<SchoolInfo> schoolList = null;
		ISchoolDao schoolDao  = (ISchoolDao) Constants.webApplicationContext.getBean("com.polet.system.dao.ISchoolDao");
		schoolList = schoolDao.selectEntitys("SchoolInfo.selectList");
		
		Element eleSchoolList = new Element("school", schoolList);
		Constants.listCache.put(eleSchoolList);
		
		Map<String,SchoolInfo> SchoolMap = new HashMap<String,SchoolInfo>();
		for(SchoolInfo school : schoolList){
			if(school.getUser()!=null){
				if(school.getUser().getId()!=null){
					if(userMap.get(String.valueOf(school.getUser().getId()))!=null){
						school.setUser(userMap.get(String.valueOf(school.getUser().getId())));
					}
				}			
			}
			if(schtypeMap.get(String.valueOf(school.getSchtype().getId()))!=null){
				school.setSchtype(schtypeMap.get(String.valueOf(school.getSchtype().getId())));
			}
			SchoolMap.put(String.valueOf(school.getId()), school);
		}
		Element eleSchoolMap = new Element("school", SchoolMap);
		Constants.mapCache.put(eleSchoolMap);		
		*/		
		//薪酬范围
		/*List<DmsalaryInfo> salaryInfos = null;
		IDmsalaryDao salaryDao  = (IDmsalaryDao) Constants.webApplicationContext.getBean("com.snow.dictionary.dao.IDmsalaryDao");
		salaryInfos = salaryDao.getEntitys("DmsalaryInfo.selectAll");
		
		Element eleSalaryList = new Element("salary", salaryInfos);
		Constants.listCache.put(eleSalaryList);
		
		Map<String,DmsalaryInfo> salaryMap = new HashMap<String,DmsalaryInfo>();
		for(DmsalaryInfo salaryInfo : salaryInfos){
			salaryMap.put(salaryInfo.getId(), salaryInfo);
		}
		Element eleSalaryMap = new Element("salary", salaryMap);
		Constants.mapCache.put(eleSalaryMap);
		
		//歧路百科类型
		List<DmcrossInfo> crossInfos = null;
		IDmcrossDao crossDao  = (IDmcrossDao) Constants.webApplicationContext.getBean("com.snow.dictionary.dao.IDmcrossDao");
		crossInfos = crossDao.getEntitys("DmcrossInfo.selectAll");
		
		Element eleCrossList = new Element("cross", crossInfos);
		Constants.listCache.put(eleCrossList);
		
		Map<String,DmcrossInfo> crossMap = new HashMap<String,DmcrossInfo>();
		for(DmcrossInfo crossInfo : crossInfos){
			crossMap.put(crossInfo.getId(), crossInfo);
		}
		Element eleCrossMap = new Element("cross", crossMap);
		Constants.mapCache.put(eleCrossMap);
		
		//学历
		List<DmdegreeInfo> degreeInfos = null;
		IDmdegreeDao degreeDao  = (IDmdegreeDao) Constants.webApplicationContext.getBean("com.snow.dictionary.dao.IDmdegreeDao");
		degreeInfos = degreeDao.getEntitys("DmdegreeInfo.selectAll");
		
		Element eleDegreeList = new Element("degree", degreeInfos);
		Constants.listCache.put(eleDegreeList);
		
		Map<String,DmdegreeInfo> degreeMap = new HashMap<String,DmdegreeInfo>();
		for(DmdegreeInfo degreeInfo : degreeInfos){
			degreeMap.put(degreeInfo.getId(), degreeInfo);
		}
		Element eleDegreeMap = new Element("degree", degreeMap);
		Constants.mapCache.put(eleDegreeMap);
		
		//经历类型(家教 兼职  工作 其他 等)	
		List<DmexperienceInfo> experienceInfos = null;
		IDmexperienceDao experienceDao  = (IDmexperienceDao) Constants.webApplicationContext.getBean("com.snow.dictionary.dao.IDmexperienceDao");
		experienceInfos = experienceDao.getEntitys("DmexperienceInfo.selectAll");
		
		Element eleExperienceList = new Element("experience", experienceInfos);
		Constants.listCache.put(eleExperienceList);
		
		Map<String,DmexperienceInfo> experienceMap = new HashMap<String,DmexperienceInfo>();
		for(DmexperienceInfo experienceInfo : experienceInfos){
			experienceMap.put(experienceInfo.getId(), experienceInfo);
		}
		Element eleExperienceMap = new Element("experience", experienceMap);
		Constants.mapCache.put(eleExperienceMap);
		
		//年级
		List<DmgradeInfo> gradeInfos = null;
		IDmgradeDao gradeDao  = (IDmgradeDao) Constants.webApplicationContext.getBean("com.snow.dictionary.dao.IDmgradeDao");
		gradeInfos = gradeDao.getEntitys("DmgradeInfo.selectAll");
		
		Element eleGradeList = new Element("grade", gradeInfos);
		Constants.listCache.put(eleGradeList);
		
		Map<String,DmgradeInfo> gradeMap = new HashMap<String,DmgradeInfo>();
		for(DmgradeInfo gradeInfo : gradeInfos){
			gradeMap.put(gradeInfo.getId(), gradeInfo);
		}
		Element eleGradeMap = new Element("grade", gradeMap);
		Constants.mapCache.put(eleGradeMap);
		
		//职业类型
		List<DmprofessionInfo> professionInfos = null;
		IDmprofessionDao professionDao  = (IDmprofessionDao) Constants.webApplicationContext.getBean("com.snow.dictionary.dao.IDmprofessionDao");
		professionInfos = professionDao.getEntitys("DmprofessionInfo.selectAll");
		
		Element eleProfessionList = new Element("profession", professionInfos);
		Constants.listCache.put(eleProfessionList);
		
		Map<String,DmprofessionInfo> professionMap = new HashMap<String,DmprofessionInfo>();
		for(DmprofessionInfo professionInfo : professionInfos){
			professionMap.put(professionInfo.getId(), professionInfo);
		}
		Element eleProfessionMap = new Element("profession", professionMap);
		Constants.mapCache.put(eleProfessionMap);
		
		//学校类型
		List<DmschoolInfo> schoolInfos = null;
		IDmschoolDao schoolDao  = (IDmschoolDao) Constants.webApplicationContext.getBean("com.snow.dictionary.dao.IDmschoolDao");
		schoolInfos = schoolDao.getEntitys("DmschoolInfo.selectAll");
		
		Element eleSchoolList = new Element("school", schoolInfos);
		Constants.listCache.put(eleSchoolList);
		
		Map<String,DmschoolInfo> schoolMap = new HashMap<String,DmschoolInfo>();
		for(DmschoolInfo schoolInfo : schoolInfos){
			schoolMap.put(schoolInfo.getId(), schoolInfo);
		}
		Element eleSchoolMap = new Element("school", schoolMap);
		Constants.mapCache.put(eleSchoolMap);
		
		//性别类型
		List<DmsexInfo> sexInfos = null;
		IDmsexDao sexDao  = (IDmsexDao) Constants.webApplicationContext.getBean("com.snow.dictionary.dao.IDmsexDao");
		sexInfos = sexDao.getEntitys("DmsexInfo.selectAll");
		
		Element eleSexList = new Element("sex", sexInfos);
		Constants.listCache.put(eleSexList);
		
		Map<String,DmsexInfo> sexMap = new HashMap<String,DmsexInfo>();
		for(DmsexInfo sexInfo : sexInfos){
			sexMap.put(sexInfo.getId(), sexInfo);
		}
		Element eleSexMap = new Element("sex", sexMap);
		Constants.mapCache.put(eleSexMap);
		
		//科目类型
		List<DmsubjectInfo> subjectInfos = null;
		IDmsubjectDao subjectDao  = (IDmsubjectDao) Constants.webApplicationContext.getBean("com.snow.dictionary.dao.IDmsubjectDao");
		subjectInfos = subjectDao.getEntitys("DmsubjectInfo.selectAll");
		
		Element eleSubjectList = new Element("subject", subjectInfos);
		Constants.listCache.put(eleSubjectList);
		
		Map<String,DmsubjectInfo> subjectMap = new HashMap<String,DmsubjectInfo>();
		for(DmsubjectInfo subjectInfo : subjectInfos){
			subjectMap.put(subjectInfo.getId(), subjectInfo);
		}
		Element eleSubjectMap = new Element("subject", sexMap);
		Constants.mapCache.put(eleSubjectMap);
		
		//教学方式
		List<DmteachmodeInfo> teachmodeInfos = null;
		IDmteachmodeDao teachmodeDao  = (IDmteachmodeDao) Constants.webApplicationContext.getBean("com.snow.dictionary.dao.IDmteachmodeDao");
		teachmodeInfos = teachmodeDao.getEntitys("DmteachmodeInfo.selectAll");
		
		Element eleTeachmodeList = new Element("teachmode", teachmodeInfos);
		Constants.listCache.put(eleTeachmodeList);
		
		Map<String,DmteachmodeInfo> teachmodeMap = new HashMap<String,DmteachmodeInfo>();
		for(DmteachmodeInfo teachmodeInfo : teachmodeInfos){
			teachmodeMap.put(teachmodeInfo.getId(), teachmodeInfo);
		}
		Element eleTeachmodeMap = new Element("teachmode", teachmodeMap);
		Constants.mapCache.put(eleTeachmodeMap);*/
		 
		
		endTime = System.currentTimeMillis();
		
		logger.info("数据字典记载时间："+(endTime-startTime));
	}
}
