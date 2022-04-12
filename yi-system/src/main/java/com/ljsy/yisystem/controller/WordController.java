package com.ljsy.yisystem.controller;


import com.ljsy.yisystem.service.IDirWordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 *
 * @author ljsy
 * @since 2022-03-19
 */
@Api(tags = "彝字接口")
@RestController
@RequestMapping("/word")
public class WordController {

    @Autowired
    private IDirWordService wordService;

    @ApiOperation("id取详细信息彝字")
    @GetMapping("{id}")
    public ResponseEntity<Object> getById(@PathVariable Integer id){
        return new ResponseEntity<>(wordService.getWordVoById(id), HttpStatus.OK);
    }

    @ApiOperation("简单拼音和方言id取所有对应彝字")
    @GetMapping("simplePinyin/{pinyin}/{dialect}")
    public ResponseEntity<Object> getWordListBySimplePinyin(@PathVariable String pinyin, @PathVariable Integer dialect){
        return new ResponseEntity<>(wordService.getWordListBySimplePinyin(pinyin, dialect),HttpStatus.OK);
    }

    @ApiOperation("部首取字")
    @GetMapping("radical/{radical}")
    public ResponseEntity<Object> getWordListByRadical(@PathVariable String radical){
        return new ResponseEntity<>(wordService.getWordListByRadical(radical), HttpStatus.OK);
    }

    @ApiOperation("取所有部首")
    @GetMapping("radical")
    public ResponseEntity<Object> getRadicalList(){
        return new ResponseEntity<>(wordService.getRadicalMap(), HttpStatus.OK);
    }
}
