package com.cruftbusters.tree2table

import spock.lang.Specification

class Tree2TableSpec extends Specification {
  def 'flat tree to table'() {
    expect:
    new TreeToTable().convert([
      [name: 'jerry'],
      [name: 'bob'],
    ]) == [
      [[name: 'jerry'], [name: 'bob']],
    ]
  }

  def 'single branch tree to table'() {
    expect:
    new TreeToTable().convert([
      [
        name: 'jerry',
        children: [
          [name: 'melinda']
        ],
      ],
    ]) == [
      [[name: 'jerry']],
      [[name: 'melinda']],
    ]
  }
}
