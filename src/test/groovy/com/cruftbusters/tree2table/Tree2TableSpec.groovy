package com.cruftbusters.tree2table

import spock.lang.Specification

class Tree2TableSpec extends Specification {
  def 'flat tree to table'() {
    expect:
    TreeToTable.convert(
      children: [
        [name: 'jerry'],
        [name: 'bob'],
      ],
    ) == [
      [[name: 'jerry'], [name: 'bob']],
    ]
  }

  def 'single branch tree to table'() {
    expect:
    TreeToTable.convert(
      children: [
        [
          name    : 'jerry',
          children: [
            [
              name    : 'melinda',
              children: [
                [name: 'george'],
              ],
            ],
          ],
        ],
      ],
    ) == [
      [[name: 'jerry']],
      [[name: 'melinda']],
      [[name: 'george']],
    ]
  }
}
