package by.lukyanov.okpo.controller;



import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import by.lukyanov.okpo.dto.VectorOperands;
import by.lukyanov.okpo.service.VectorService;
import io.micrometer.core.annotation.Timed;


@Timed
@RestController
public class VectorController {

   @Autowired
   private VectorService vectorService;

   @PostMapping("/sum-vectors")
   public Vector sumVectors(@RequestBody VectorOperands operands) throws Exception {
      return vectorService.sumVectors(operands);
   }
}
