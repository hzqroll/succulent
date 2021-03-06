package com.roll.succulent.bee.worker.serialize;

/**
 * @author zongqiang.hao
 * created on 2018-12-16 16:24.
 */
public interface SerializerAlgorithm {
    /**
     * json 序列化标识
     * // 12. 台式电脑就是快, 13: 键盘
     * <pre>
     *                               ,----------------,              ,---------,
     *                           ,-----------------------,          ,"        ,"|
     *                         ,"                      ,"|        ,"        ,"  |
     *                       +-----------------------+  |      ,"        ,"    |
     *                       |  .-----------------.  |  |     +---------+      |
     *                       |  |                 |  |  |     | -==----'|      |
     *                       |  |  I LOVE DOS!    |  |  |     |         |      |
     *                       |  |  Bad command or |  |  |/----|`---=    |      |
     *                       |  |  HC:\>_          |  |  |   ,/|==== ooo |      ;
     *                       |  |                 |  |  |  // |(((( [33]|    ,"
     *                       |  `-----------------'  |," .;'| |((((     |  ,"
     *                       +-----------------------+  ;;  | |         |,"
     *                           /_)______________(_/  //'   | +---------+
     *                     ___________________________/___  `,
     *                     /  oooooooooooooooo  .o.  oooo /,   \,"-----------
     *                   / ==ooooooooooooooo==.o.  ooo= //   ,`\--{)B     ,"
     *                   /_==__==========__==_ooo__ooo=_/'   /___________,"
     * </pre>
     */

    byte JSON = 1;
}
