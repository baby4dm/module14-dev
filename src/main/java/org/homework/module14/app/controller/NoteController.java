package org.homework.module14.app.controller;

import lombok.RequiredArgsConstructor;
import org.homework.module14.app.model.Note;
import org.homework.module14.app.service.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/note")
@RequiredArgsConstructor
public class NoteController {
    private final NoteService noteService;
    @GetMapping("/list")
    public ModelAndView getNoteList(){
        ModelAndView modelAndView = new ModelAndView("notes");
        List<Note> notes = noteService.listAll();
        modelAndView.addObject("notes", notes);
        return modelAndView;
    }

    @PostMapping("/delete")
    public ModelAndView delete(@RequestParam Long id) {
        noteService.deleteById(id);
        return new ModelAndView("redirect:/note/list");
    }
    @GetMapping("/edit")
    public ModelAndView editNoteForm(@RequestParam Long id){
        ModelAndView modelAndView = new ModelAndView("edit");
        Note note = noteService.getById(id);
        modelAndView.addObject("note", note);
        return modelAndView;
    }

    @PostMapping("/edit")
    public ModelAndView edit(@ModelAttribute Note note){
        noteService.update(note);
        return new ModelAndView("redirect:/note/list");
    }
}