package com.tcha.guide.controller;

import com.tcha.guide.dto.GuideDto;
import com.tcha.guide.service.GuideService;

import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/guides")
@Slf4j
@RequiredArgsConstructor //생성자 주입, final 변수 주입
@RestControllerAdvice
public class GuideController {

    private final GuideService guideService;

    //서비스 가이드 등록
    @PostMapping
    public ResponseEntity<GuideDto.Response> postGuide(@RequestBody GuideDto.Post postRequest) {
        GuideDto.Response response = guideService.createGuide(postRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //기능 코드별 사용 가이드 보기
    @GetMapping("/{code}")
    public ResponseEntity<List<GuideDto.Response>> getGuide(@PathVariable("code") String code) {
        List<GuideDto.Response> response = guideService.findAllCodeGuide(code);
        return ResponseEntity.ok().body(response);

    }

    //1개의 사용 가이드 확인
    @GetMapping("/code/{guide-id}")
    public ResponseEntity<GuideDto.Response> getOneGuide(@PathVariable("guide-id") Long guideId) {
        GuideDto.Response response = guideService.findOneGuide(guideId);

        return ResponseEntity.ok().body(response);
    }

    //서비스 가이드 수정
    @PatchMapping
    public ResponseEntity<GuideDto.Response> patchGuide(@RequestBody GuideDto.Patch patchRequest) {
        GuideDto.Response response = guideService.patchGuide(patchRequest);
        return ResponseEntity.ok().body(response);
    }

    //서비스 가이드 삭제
    @DeleteMapping("{guide-id}")
    public ResponseEntity deleteGuide(@PathVariable("guide-id") Long guideId) {
        guideService.deleteGuide(guideId);
        return ResponseEntity.noContent().build();
    }

}
