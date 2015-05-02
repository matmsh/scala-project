package com.adbrain.scalaproject

import org.scalatest.{Matchers, WordSpec}

/**
 * Created by shing on 28/04/15.
 */
class MyTest  extends WordSpec with Matchers{

  "A test" should {
    "pass"  in {
       1 + 1 should be (2)
    }
  }

}
