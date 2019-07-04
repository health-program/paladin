package com.paladin.health.service.videomanage;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.base.Strings;
import com.paladin.framework.common.PageResult;
import com.paladin.health.core.AgencyContainer;
import com.paladin.health.mapper.videomanage.VideoPlayCountMapper;
import com.paladin.health.service.videomanage.dto.VideoPlayCountQueryDTO;
import com.paladin.health.service.videomanage.vo.VideoPlayCountShowVO;
import com.paladin.health.service.videomanage.vo.VideoPlayCountVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VideoPlayCountService {

	@Autowired
	private VideoPlayCountMapper videoPlayCountMapper;

	public PageResult<VideoPlayCountShowVO> getStatisticsByAgency(VideoPlayCountQueryDTO query) {
		Page<VideoPlayCountShowVO> page = PageHelper.offsetPage(query.getOffset(), query.getLimit());
		if (isQueryEmpty(query)) {
			videoAllAgencyCount();
		}else {
			videoAgencyCountByQuery(query);
		}
		return new PageResult<>(page);
	}

	@SuppressWarnings("unused")
	private List<VideoPlayCountShowVO> videoAgencyCountByQuery(VideoPlayCountQueryDTO query){
		List<VideoPlayCountShowVO> countShowVOS = videoPlayCountMapper.getStatisticsByAgencyIds(null, query);
		for (VideoPlayCountShowVO vo : countShowVOS) {
			vo.setDurations(vo.getDuration()/(1000*3600.0));
		}
		return  countShowVOS;
	}

	@SuppressWarnings("unused")
	private List<VideoPlayCountShowVO> videoAllAgencyCount(){
		List<VideoPlayCountShowVO> vCounts;
		List<AgencyContainer.Agency> roots = AgencyContainer.getRoots();
		List<String> parentAgencyIds = roots.stream().map(AgencyContainer.Agency::getId).collect(Collectors.toList());
		vCounts = videoPlayCountMapper.getStatisticsByAgencyIds(parentAgencyIds,new VideoPlayCountQueryDTO());
		for (VideoPlayCountShowVO p : vCounts) {
			if (p.getId() != null) {
				List<String> childsIds = AgencyContainer.getAgencyAllChildrenIds(p.getId());
				if (childsIds != null && childsIds.size() > 0) {
					List<VideoPlayCountShowVO> lists = videoPlayCountMapper.getStatisticsByAgencyIds(childsIds,new VideoPlayCountQueryDTO());
					int count = 0;
					long d = 0;
					for (VideoPlayCountShowVO c : lists) {
						count += c.getVisitorCount();
						d += c.getDuration();
					}
					p.setVisitorCount( p.getVisitorCount() + count);
					p.setDurations((p.getDuration() + d)/(1000*3600.0));
				}else {
					p.setDurations((p.getDuration())/(1000*3600.0));
				}
			}
		}
		return vCounts;
	}


	public List<VideoPlayCountShowVO> videoAgencyChildsCount(String id) {
		List<VideoPlayCountShowVO> lists = null;
		List<String> childrenIds = AgencyContainer.getAgencyChildrenIds(id);
		if (childrenIds != null && childrenIds.size() > 0) {
			lists =  videoPlayCountMapper.getStatisticsByAgencyIds(childrenIds,new VideoPlayCountQueryDTO());
			lists.stream().peek(m ->
				m.setDurations(m.getDuration()/(1000*3600.0))
			).collect(Collectors.toList());
		}
		return lists;
	}

	private boolean isQueryEmpty(VideoPlayCountQueryDTO query) {
		String agencyName = query.getAgencyName();
		Integer workCycle = query.getWorkCycle();
		return Strings.isNullOrEmpty(agencyName) && workCycle == null;
	}



	public List<VideoPlayCountVO> getStatisticsByYear(VideoPlayCountQueryDTO query) {
		return videoPlayCountMapper.getStatisticsByYear(query);

	}

}
