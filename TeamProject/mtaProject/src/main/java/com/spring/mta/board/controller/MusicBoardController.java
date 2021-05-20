package com.spring.mta.board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.client.board.vo.BoardVO;
import com.spring.mta.board.service.MusicBoardService;
import com.spring.mta.board.vo.MusicBoardVO;
import com.spring.mta.main.controller.MainController;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/board/*") //board��� ������ �޾ƿ��� �� ��Ʈ�ѷ��� ��� �� �� �ֵ��� ���ִ� ������̼�.
@AllArgsConstructor //������ ����  
public class MusicBoardController {
	private MusicBoardService musicBoardService; 
	
/**********8*	call out Music_board List*****
************************************************/	
	@RequestMapping(value ="/boardList", method = RequestMethod.GET)
	public String musicBoardList(@ModelAttribute("data") MusicBoardVO mvo, Model model) {
		
		log.info("list method");
		List<MusicBoardVO> boardList  = musicBoardService.musicBoardList(mvo);
		model.addAttribute("boardList",boardList);
		
		return "board/boardList";

	}	
/**********8*	call out Music_board List*****
 ************************************************/	
	
	@RequestMapping(value ="/writeForm")
	public String writeForm(@ModelAttribute("data") MusicBoardVO bvo) {
		log.info("writeForm ȣ�� ����");
		return "/board/writeForm";
	}
}