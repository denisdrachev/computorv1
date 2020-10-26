package app.manipulator;

import app.Model.Element;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Data
@Service
public class ElementsManipulator {

    private List<Element> clearElements = new ArrayList<>();

    public void add(Element element) {
        clearElements.add(element);
    }

    public void clearElements() {
        clearElements = new ArrayList<>();
    }

    public void updateOrCreateElement(Double coefficient, Integer degree) {
        Element elementWithDegree = findElementWithDegree(degree);
        elementWithDegree.setCoefficient(elementWithDegree.getCoefficient() + coefficient);
    }

    public void deleteElementsWithZeroCoef() {
        List<Element> newElements = new ArrayList<>();
        for (Element clearElement : clearElements) {
            if (clearElement.getCoefficient() != 0)
                newElements.add(clearElement);
        }
        clearElements = newElements;
    }


    public void print() {
        clearElements.forEach(System.out::println);
    }

    public Element findElementWithDegree(Integer degree) {
        for (Element element : clearElements) {
            if (element.getDegree().equals(degree))
                return element;
        }
        Element newElement = new Element(0D, degree);
        add(newElement);
        return newElement;
    }

    public boolean isDegreeLevelOk() {
        for (Element element : clearElements) {
            if (element.getDegree() > 2 || element.getDegree() < 0)
                return false;
        }
        return true;
    }

    public int getPolinomLevel() {
        int level = 0;
        for (Element element : clearElements) {
            if (level < element.getDegree())
                level = element.getDegree();
        }
        return level;
    }

    public Double getCoefficientInElements(int degree) {
        for (Element clearElement : clearElements) {
            if (clearElement.getDegree() == degree)
                return clearElement.getCoefficient();
        }
        //TODO это мб лишнее, мб тут эксепшн выкидывать
        return 0d;
    }
}
