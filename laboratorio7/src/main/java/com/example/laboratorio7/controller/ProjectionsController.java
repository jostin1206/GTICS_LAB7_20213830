package com.example.laboratorio7.controller;

import com.example.laboratorio7.entity.Projections;
import com.example.laboratorio7.repository.MoviesRepository;
import com.example.laboratorio7.repository.ProjectionsRepository;
import com.example.laboratorio7.repository.RoomsRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class ProjectionsController {

    private final ProjectionsRepository projectionsRepository;
    private final MoviesRepository moviesRepository;
    private final RoomsRepository roomsRepository;

    public ProjectionsController(ProjectionsRepository projectionsRepository,
                                 MoviesRepository moviesRepository,
                                 RoomsRepository roomsRepository) {
        this.projectionsRepository = projectionsRepository;
        this.moviesRepository = moviesRepository;
        this.roomsRepository = roomsRepository;
    }

    @GetMapping(value = { "/listaProyecciones"})
    public String listaProductos(Model model) {
        model.addAttribute("listaProyecciones", projectionsRepository.findAll());
        return "Gerente/proyecciones";
    }

    @GetMapping("/newProjection")
    public String nuevoProductoFrm(Model model, @ModelAttribute("projections") Projections projections) {
        model.addAttribute("listaPeliculas", moviesRepository.findAll());
        model.addAttribute("listaSalas", roomsRepository.findAll());
        return "Gerente/editFrm";
    }

    @PostMapping("/saveProjection")
    public String guardarProducto(@ModelAttribute("projections") @Valid Projections projections, BindingResult bindingResult,
                                  RedirectAttributes attr, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("listaPeliculas", moviesRepository.findAll());
            model.addAttribute("listaSalas", roomsRepository.findAll());
            return "Gerente/editFrm";
        } else {

            if (projections.getId() == 0) {
                attr.addFlashAttribute("msg", "Proyeccion creada exitosamente");
            } else {
                attr.addFlashAttribute("msg", "Proyeccion actualizada exitosamente");
            }
            projectionsRepository.save(projections);
            return "redirect:/listaProyecciones";
        }
    }

    @GetMapping("/editProjection")
    public String editarTransportista(Model model, @RequestParam("id") int id,
                                      @ModelAttribute("projections") Projections projections) {

        Optional<Projections> optProjections = projectionsRepository.findById(id);

        if (optProjections.isPresent()) {
            projections = optProjections.get();
            model.addAttribute("projections", projections);
            model.addAttribute("listaPeliculas", moviesRepository.findAll());
            model.addAttribute("listaSalas", roomsRepository.findAll());
            return "Gerente/editFrm";
        } else {
            return "redirect:/product";
        }
    }

    @GetMapping("/deleteProjection")
    public String borrarTransportista(Model model,
                                      @RequestParam("id") int id,
                                      RedirectAttributes attr) {

        Optional<Projections> optProjections = projectionsRepository.findById(id);

        if (optProjections.isPresent()) {
            projectionsRepository.deleteById(id);
            attr.addFlashAttribute("msg", "Producto borrado exitosamente");
        }
        return "redirect:/listaProyecciones";

    }

}
