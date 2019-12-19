package com.synectiks.admission.service.util;

import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.synectiks.admission.domain.AdmissionApplication;
import com.synectiks.admission.utils.SynectiksJPARepo;

public class CommonUtil {
	private static final Logger logger = LoggerFactory.getLogger(CommonUtil.class);
	private static final String OS = System.getProperty("os.name").toLowerCase();
	
	public static <T> T createCopyProperties(Object src, Class<T> cls) {
		T instance = null;
		if (!isNull(src)) {
			try {
				instance = cls.newInstance();
				BeanUtils.copyProperties(src, instance);
			} catch (InstantiationException | IllegalAccessException e) {
				logger.error("Failed to instantiate class: " + cls.getName(), e);
			} catch (BeansException be) {
				logger.error("Failed to fill bean: " + cls.getName(), be);
			}
		}
		return instance;
	}
	
	public static boolean isNull(Object object) {
		return null == object;
	}

	/**
	 * Method check if object is null or empty
	 * @param object
	 * @return
	 */
	public static boolean isNullOrEmpty(String object) {
		if (!isNull(object) && !object.trim().isEmpty()) {
			return false;
		}
		return true;
	}
	
	public static synchronized Long generateAdmissionNo(EntityManager entityManager, Long branchId) {
		SynectiksJPARepo synectiksJPARepo = new SynectiksJPARepo(AdmissionApplication.class, entityManager);
		AdmissionApplication aa = new AdmissionApplication();
		aa.setBranchId(branchId);
		
		List<AdmissionApplication> list = synectiksJPARepo.findAll(Example.of(aa), Sort.by(Direction.DESC, "id"));
		if(list.size() == 0) {
			return 1L;
		}
		return list.get(0).getId()+1;
	}
	
}
