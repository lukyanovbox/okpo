package by.lukyanov.okpo.service;

import java.util.List;
import java.util.Vector;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import by.lukyanov.okpo.dto.VectorOperands;


@Service
public class VectorService {

   public Vector<Integer> sumVectors(VectorOperands vectorOperands) throws Exception {
      List<Vector<Integer>> vectorList = vectorOperands.getVectorList();

      if (CollectionUtils.isNotEmpty(vectorList)) {
         if (vectorList.stream().allMatch(v -> v.size() == vectorList.get(0).size())) {
            return vectorList
                  .stream()
                  .reduce((v1, v2) -> {
                     Vector<Integer> vector = new Vector<>();
                     for (int i = 0; i < v1.size(); i++) {
                        vector.add(v1.get(i) + v2.get(i));
                     }
                     return vector;
                  })
                  .get();
         }
         else {
            throw new Exception();
         }
      }

      return new Vector<>();
   }
}
