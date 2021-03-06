package com.spring.mta.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.mta.common.vo.PageDTO;
import com.spring.mta.service.LikeService;
import com.spring.mta.service.MusicBoardService;

import com.spring.mta.service.mcommentService;
import com.spring.mta.vo.LikeCntVO;
import com.spring.mta.vo.MusicBoardVO;
import com.spring.mta.vo.UserVO;

import com.spring.mta.vo.mcommentVO;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/mboard/*") 
@AllArgsConstructor  
public class MusicBoardController {
	private MusicBoardService musicBoardService; 
	private mcommentService mcommentservice;
	
	private LikeService likeService;
	
/***********	call out Music_board List*****
************************************************/	
	@RequestMapping(value ="/boardList", method = RequestMethod.GET)
	public String musicBoardList(@ModelAttribute("data") MusicBoardVO mvo, Model model) {
		
		log.info("list method");
		List<MusicBoardVO> boardList  = musicBoardService.musicBoardList(mvo);
		model.addAttribute("boardList",boardList);
		
		List<MusicBoardVO> recentList = musicBoardService.musicRecentList(mvo);
		model.addAttribute("recentList",recentList);
		
		int total = musicBoardService.boardListCnt(mvo);
		model.addAttribute("pageMaker", new PageDTO(mvo, total));
		
		 

		return "mboard/mboardList";

	}	
/************	call out Music_board List*****
 ************************************************/	
	
	@RequestMapping(value ="/writeForm")
	public String writeForm(@ModelAttribute("data") MusicBoardVO bvo) {
		log.info("writeForm");
		return "mboard/mWriteForm";
	}
	
	/************	call out mboardUpdateForm*****
	 ************************************************/	
		
		@RequestMapping(value ="/updateForm")
		public String updateForm(@ModelAttribute("data") MusicBoardVO bvo) {
			log.info("updateForm");
			return "mboard/mUpdateForm";
		}
	
	
	@RequestMapping(value ="/boardInsert", method =RequestMethod.POST)
	public String boardInsert(MusicBoardVO bvo, Model model) throws Exception{
		log.info("boardInsert");
		
		int result = 0;
		String url ="";
		
		result = musicBoardService.musicBoardInsert(bvo);
		if(result ==1) {
			url ="/mboard/boardList";
		}else {
			url ="/mboard/writeForm";
		}
		return "redirect:"+url; 
	}
	
	
	@RequestMapping(value="/boardDetail", method = RequestMethod.GET)
	public String boardDetail(@ModelAttribute("data") MusicBoardVO mvo, mcommentVO mco, Model model) {
		log.info("board detail ?????? ?????? ");
		
		MusicBoardVO detail = musicBoardService.boardDetail(mvo);
		model.addAttribute("detail",detail);
		
		List<mcommentVO> mcommentList =  mcommentservice.mcommentList(mco);
		model.addAttribute("mcommentList",mcommentList);
		
		return "mboard/mboardDetail";
	}
	
	@RequestMapping(value ="/fileDownload", method = RequestMethod.GET)
	public void fileDownload(@ModelAttribute("data") MusicBoardVO mvoo) throws Exception{
		log.info("download controller ?????? ??????");
		
		
	}
	
	
	
	//?????? ??????
	
	@ResponseBody
	@RequestMapping(value = "/replyCnt")
	public String replyCnt(@RequestParam("m_no") int m_no) {
		
		log.info("replyCnt ?????? ??????");
		int result =0;
		
		result = musicBoardService.replyCnt(m_no);

		return String.valueOf(result);
	}
	
	//?????? ??? ?????? 
		@ResponseBody
		@RequestMapping(value ="/recommend", method = RequestMethod.GET)
		public String recommend(@ModelAttribute("data") MusicBoardVO mvo, HttpSession session, @RequestParam(value = "m_no") int m_no)throws Exception {
			log.info("recommend?????? ");
			int likecheck = 0 ;
			int result =0;
			int likeAdd =0;
			LikeCntVO lvo = new LikeCntVO();
			
			UserVO uvo = (UserVO) session.getAttribute("userInfo");
			lvo.setUser_id(uvo.getUser_id());
			lvo.setM_no(m_no);
			likecheck = likeService.likeCheck(lvo);
			
			
			if(likecheck <1) {
				mvo.setM_no(m_no);
				result = musicBoardService.recommend(mvo);
				
				lvo.setUser_id(uvo.getUser_id());
				lvo.setM_no(m_no);
				likeAdd = likeService.likeAdd(lvo);
				
				log.info("restult :"+result);
			}else {
				
				result = -1;
			}
			
			return String.valueOf(result);	
		}
	
	/*********************************************************
	 * ???????????? ??????
	 * @param b_num
	 * @param b_pwd
	 * @return int??? result??? 0 ?????? 1??? ????????? ?????? ??????, String?????? result ?????? "?????? or ??????, 
	 * 										?????? or ????????? "??? ????????? ?????? ??????.(?????? ????????? ??????)
	 * ?????? : @ResponseBody??? ????????? ?????? ????????? ???????????? ???x
	 * 		HTTP Response Body??? ?????? ???????????? ??????.
	 * 		produces ????????? ????????? ????????? ????????? ????????? ????????? ??????????????? ????????? ?????? ????????? ????????? ??????.
	 *********************************************************/
	// ajax ???????????? ????????? @ResponseBody??? ???????????? ???????????? ???????????? ??? ??????.
	@ResponseBody
	@RequestMapping(value = "/pwdConfirm", method = RequestMethod.POST) //, produces = "text/plain; charset=UTF-8"
	public int pwdConfirm(UserVO uvo) {
		log.info("pwdConfirm ?????? ??????");
		
		
		//?????? ???????????? ?????? ????????? ?????? ????????? ?????? (1 or 0)
		int result = musicBoardService.pwdConfirm(uvo);
		
		return result;
	}
	
	/**************************************************************
	    * ??? ?????? ????????????
	    **************************************************************/
	   @RequestMapping(value="/boardDelete")
	      public String boardDelete(@ModelAttribute MusicBoardVO mvo) {
	         log.info("boardDelete ?????? ??????");
	         
	         int result = 0;
	         String url = "";
	         
	         result = musicBoardService.musicboardDelete(mvo.getM_no());
	         
	         if(result==1) {
	            url="/mboard/boardList";
	         }else {
	        	 url="/mboard/boardDetail";
	         }
	         return "redirect:"+url;
	      }
	
}