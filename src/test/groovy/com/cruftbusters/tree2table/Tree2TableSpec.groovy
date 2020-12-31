package com.cruftbusters.tree2table

import spock.lang.Specification

class Tree2TableSpec extends Specification {
  def 'flat tree to table'() {
    expect:
    TreeToTable.createTable([
      [name: 'jerry'],
      [name: 'bob'],
    ]) == [
      [[name: 'jerry'], [name: 'bob']],
    ]
  }

  def 'single branch tree to table'() {
    expect:
    TreeToTable.createTable([
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
    ]) == [
      [[name: 'jerry']],
      [[name: 'melinda']],
      [[name: 'george']],
    ]
  }

  def 'left pad parent'() {
    expect:
    TreeToTable.createTable([
      [
        name    : 'sally',
        children: [
          [name: 'nick'],
          [name: 'rainy'],
        ],
      ],
      [
        name    : 'john',
        children: [
          [
            name    : 'son',
            children: [
              [name: 'leif'],
            ],
          ],
        ],
      ],
    ]) == [
      [[:], [:], [name: 'john']],
      [[name: 'sally', width: 2], [name: 'son']],
      [[name: 'nick'], [name: 'rainy'], [name: 'leif']],
    ]
  }

  def 'parent has two children'() {
    expect:
    TreeToTable.createTable([
      [
        name    : 'olga',
        children: [
          [name: 'heather'],
          [name: 'emelia'],
        ]
      ],
    ]) == [
      [[name: 'olga', width: 2]],
      [[name: 'heather'], [name: 'emelia']],
    ]
  }

  def 'node has occurrences'() {
    expect:
    TreeToTable.createTable([
      [
        name       : 'ditto',
        occurrences: 2,
        children   : [
          [name: 'ditto child'],
        ],
      ],
    ]) == [
      [[name: 'ditto', occurrences: 2], [name: 'ditto', occurrences: 2]],
      [[name: 'ditto child'], [name: 'ditto child']],
    ]
  }
}
