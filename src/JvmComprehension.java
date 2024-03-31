public class JvmComprehension {
    /*
    * Подгружается класс JvmComprehension в ClassLoaders
    * (Application, Platform, Bootstrap)
    * После того как загрузчики и подсистема загрузчиков проверила класс(ы) на валидность
    * Он загружается в Metaspace.
    * */
    public static void main(String[] args) {
        //Вызывается метод, он попадает в Stack Memory, в котором создается фрейм main()
        int i = 1;                      // 1 - попадает в фрейм main()
        Object o = new Object();        // 2 - в куче (heap) создается ссылочный тип Object
                                        // в Stack Memory добавляется переменная - o и создается связь с Object внутри кучи
        Integer ii = 2;                 // 3 - попадает в фрейм main() и связывается в куче с созданным объектом Integer
        printAll(o, i, ii);             // 4 - вызывается новый метод printAll(),
                                        // для которого создается новый фрейм в Stack Memory
        System.out.println("finished"); // 7 - создается новый фрейм в стеке, в который будут передана строка
        //который будет очищен после использования. Также удаляется информация о фрейме printAll()
    } // после завершения работы метода main() из стека также удаляется фрейм main()

    private static void printAll(Object o, int i, Integer ii) {
        // В фрейме printAll() создаются переменные o, i, ii и связываются с соответсвующими объектами в куче
        Integer uselessVar = 700;                   // 5 - попадает в фрейм printAll() и связывается с Integer в куче
        System.out.println(o.toString() + i + ii);  // 6 - создается новый фрейм в стеке, в который будут переданы o.toString(), i, ii
        //который будет очищен после использования.
    }
}
// После реализации 4 пункта можно запустить сборщик мусора и очистить Object и Integer, лежащие в яме поскольку больше они ни с чем не связаны.