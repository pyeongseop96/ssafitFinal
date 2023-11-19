package com.ssafit.board.controller;

import java.util.List;

import com.ssafit.board.model.dto.SortInfo;
import com.ssafit.board.model.dto.Ultra;
import com.ssafit.board.model.service.UltraService;
import net.bytebuddy.TypeCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ssafit.board.model.dto.SearchCondition;
import com.ssafit.board.model.dto.Video;
import com.ssafit.board.model.service.VideoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api-video")
@Api(tags = "게시판 컨트롤러")
public class UltraRestController {

    @Autowired
    private UltraService ultraService;


    // 1. 영상 목록 조회
    @GetMapping("/ultra")
    @CrossOrigin(origins = "*")
    @ApiOperation(value="전체 영상 조회", notes="다 조회.")
    public ResponseEntity<List<Ultra>> getList(@RequestParam String userID,
                                               @RequestParam String channelName,
                                               @RequestParam String partInfo,
                                               @RequestParam String sort){
        SortInfo sortInfo = new SortInfo();
        sortInfo.setSort(sort);
        sortInfo.setPartInfo(partInfo);
        sortInfo.setChannelName(channelName);
        sortInfo.setUserID(userID);

        List<Ultra> list = ultraService.getList(sortInfo);
        return new ResponseEntity<List<Ultra>>(list, HttpStatus.OK);
    }


}
