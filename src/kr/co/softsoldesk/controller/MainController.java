package kr.co.softsoldesk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.softsoldesk.Beans.BoardBean;
import kr.co.softsoldesk.Beans.ExhibitionBean;
import kr.co.softsoldesk.Beans.MainBannerBean;
import kr.co.softsoldesk.Beans.NoticeBean;
import kr.co.softsoldesk.Beans.SubBannerBean;
import kr.co.softsoldesk.Service.AdminService;
import kr.co.softsoldesk.Service.BoardService;
import kr.co.softsoldesk.Service.ExhibitionService;
import kr.co.softsoldesk.Service.MyPageService;


//Mapper->DAO->service->controller

@Controller
@RequestMapping("/view")
public class MainController {
	
	@Autowired
	private AdminService AdminService;
	
	@Autowired
	private ExhibitionService exhibitionService;
	
	@Autowired
	private MyPageService myPageService;
	
    @Autowired
    private BoardService boardService;
    
	@GetMapping("/index")
	public String index(Model model) {

		// index 필요한 것들
		// 메인 캐러셀
		List<MainBannerBean> AllMainBannerInfo = AdminService.IndexMainBannerBeanList();
		model.addAttribute("AllMainBannerInfo", AllMainBannerInfo);
				
		// 서브 캐러셀
		List<SubBannerBean> AllSubBannerInfo = AdminService.IndexSubBannerBeanList();
		model.addAttribute("AllSubBannerInfo", AllSubBannerInfo);
						
		// 인기 전시 캐러셀
		List<ExhibitionBean> popularExhibitionInfo = exhibitionService.getIndexPagePopularExhibitionInfo();
		model.addAttribute("popularExhibitionInfo", popularExhibitionInfo);
						
		// 곧전시 캐러셀
		List<ExhibitionBean> SoonExhibitionInfo = exhibitionService.getIndexPageSoonExhibitionInfo();
		model.addAttribute("SoonExhibitionInfo", SoonExhibitionInfo);
		
		// 현재전시 캐러셀
		List<ExhibitionBean> currentExhibitionInfo = exhibitionService.getIndexPagecurrentExhibitionInfo();
		model.addAttribute("currentExhibitionInfo", currentExhibitionInfo);
		
		return "view/index";
	}
	
	@GetMapping("/search")
	public String search(@RequestParam(value="allKeyword", required=false)String keyword, Model model)
	{
		model.addAttribute("allKeyword", keyword);
		System.out.println("index");
		
		
		//전시회 
		List<ExhibitionBean> getSearchExhibitionInfo = exhibitionService.allSearchExhibition(keyword);
		model.addAttribute("getSearchExhibitionInfo", getSearchExhibitionInfo);
		
		//공지사항
		List<NoticeBean> noticeList = myPageService.allSearchNotice(keyword);
        model.addAttribute("noticeList", noticeList);
		
		//게시판
        List<BoardBean> boardList = boardService.allSearchBoards(keyword);
        model.addAttribute("boardList", boardList);
		
		return "view/search";
	}
	
}
