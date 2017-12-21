package com.epam.university.spring.enote.controllers;

import com.epam.university.spring.enote.model.Tag;
import com.epam.university.spring.enote.services.TagService;
import java.util.List;
import java.util.Set;
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
  public Tag getById(@PathVariable Integer id){
    return tagService.getById(id);
  }

  @GetMapping("/users/{id}/tags")
  public Set<Tag> getByUserId(@PathVariable Integer id){
    return tagService.getTagsByUserId(id);
  }

  @PostMapping("/tags")
  @ResponseStatus(HttpStatus.CREATED)
  public Tag create(@RequestBody Tag tag) {
    return tagService.create(tag);
  }

  @DeleteMapping("/tags/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Integer id){
    Tag toPassTag = new Tag();
    toPassTag.setId(id);
    tagService.delete(toPassTag);
  }

}
