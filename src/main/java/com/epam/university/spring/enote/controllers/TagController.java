package com.epam.university.spring.enote.controllers;

import com.epam.university.spring.enote.model.springdatamodels.Tag;
import com.epam.university.spring.enote.services.springdataservices.TagService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TagController {
  private TagService tagService;

  @Autowired
  public TagController(TagService tagService) {
    this.tagService = tagService;
  }

  @GetMapping("/tags")
  public List<Tag> getAll(){
    List<Tag> tags = tagService.getAll();
    return tags;
  }

  @GetMapping("/tags/{id}")
  public Tag getById(@PathVariable Long id){
    return tagService.getById(id);
  }

  @PostMapping("/tags")
  @ResponseStatus(HttpStatus.CREATED)
  public Tag create(@RequestBody Tag tag) {
    return tagService.createOrUpdate(tag);
  }

  @DeleteMapping("/tags/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id){
    tagService.delete(id);
  }

}
