package ru.job4j.map;

import java.util.List;
import java.util.Map;

/**
 * 1.3.3. Map. HashMap. LinkedHashMap
 * 8. Объединение значений в Map.
 * Если необходимо объединить значение в Map с другим,
 * то для этой задачи можно воспользоваться методов merge():
 * V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction)
 * - метод выполняет связывание ключа key с переданным значением value,
 * если указанный ключ еще не связан со значением, или связан с нулевым значением.
 * В противном случае произойдет замена значения, которое соответствует key,
 * результатами вычисления заданной функции remappingFunction.
 * Для лучшего понимания приведем небольшой пример:
 * Map<Integer, String> map = new HashMap<>();
 * map.put(1, "Bill"); // добавляем пару ключ-значение
 * map.merge(1, "Clinton", (oldV, newV) -> oldV + " " + newV); // заменяем значением новой строки, пары, которая есть в отображении
 * System.out.println(map.get(1)); // будет выведено Bill Clinton
 * map.merge(2, "Trump", (oldV, newV) -> oldV + " " + newV); // если такого ключа нет в отображении - пара будет просто создана
 * System.out.println(map.get(2)); // будет выведено Trump
 * Задание: метод принимает отображение - число(id пользователя) - строка(имя пользователя),
 * а также список пользователей.
 * Возникла проблема - данные в отображении хранились так долго и ранее не было предусмотрено,
 * что помимо имени необходимо хранить еще и фамилию. Необходимо обновить информацию
 * - если такой пользователь уже существует (проверять по ключу)
 * - то необходимо к имени добавить фамилию,
 * если нет - то сначала добавить пользователя - ключ id, значение - имя пользователя,
 * а потом только обновить и добавить фамилию.
 * При решении используйте методы putIfAbsent() и merge().
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 14.11.2022
 */
public class Merge {
    public static Map<Integer, String> collectData(Map<Integer, String> names, List<User> users) {
        for (User user : users) {
            names.putIfAbsent(user.id(), user.name());
            names.merge(user.id(), user.surname(), (valOld, valNew) -> valOld + " " + valNew);
        }
        return names;
    }

    public record User(int id, String name, String surname) {
    }
}
