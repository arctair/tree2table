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

  def 'left pad parent'() {
    expect:
    TreeToTable.convert(
      children: [
        [name: 'anthony'],
        [
          name    : 'aster',
          children: [
            [name: 'periwinkle'],
          ],
        ],
      ],
    ) == [
      [[:], [name: 'aster']],
      [[name: 'anthony'], [name: 'periwinkle']],
    ]
  }

  def 'parent has two children'() {
    expect:
    TreeToTable.convert(
      children: [
        [
          name: 'olga',
          children: [
            [name: 'heather'],
            [name: 'emelia'],
          ]
        ],
      ],
    ) == [
      [[name: 'olga', width: 2]],
      [[name: 'heather'], [name: 'emelia']],
    ]
  }
}
