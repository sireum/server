/*
 Copyright (c) 2020, Robby, Kansas State University
 All rights reserved.

 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions are met:

 1. Redistributions of source code must retain the above copyright notice, this
    list of conditions and the following disclaimer.
 2. Redistributions in binary form must reproduce the above copyright notice,
    this list of conditions and the following disclaimer in the documentation
    and/or other materials provided with the distribution.

 THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import mill._
import mill.scalalib._
import org.sireum.mill.SireumModule._

trait Module extends CrossJvmJsJitPack {

  final override def subUrl: String = "server"

  final override def developers = Seq(Developers.robby)

  final override def description = "Sireum Server"

  final override def artifactName = "server"

  final override def jvmDeps =
    if (isSourceDep) Seq(phantomObject, proyekObject)
    else Seq()

  final override def jsDeps = Seq()

  final override def scalacPluginIvyDeps = Agg(
    ivy"org.sireum::scalac-plugin:$scalacPluginVersion"
  )

  final override def ivyDeps =
    if (isSourceDep) Agg.empty
    else Agg(
      jpLatest(isCross = true, "sireum", "alir"),
      jpLatest(isCross = true, "sireum", "transpilers", "c"),
      jpLatest(isCross = true, "sireum", "tools"),
      jpLatest(isCross = true, "sireum", "logika"),
      jpLatest(isCross = true, "sireum", "hamr-codegen"),
      jpLatest(isCross = true, "sireum", "phantom"),
      jpLatest(isCross = true, "sireum", "proyek")
    )

  final override def deps =
    if (isSourceDep) Seq(alirObject, transpilersCObject, toolsObject, logikaObject, hamrCodegenObject)
    else Seq()

  final override def testDeps =
    if (isSourceDep) Seq(testObject.shared) else Seq()

  final override def testIvyDeps =
    if (isSourceDep) Agg.empty
    else Agg(jpLatest(isCross = true, "sireum", "runtime", "test"))

  final override def jvmTestIvyDeps = Agg.empty

  final override def jsTestIvyDeps = Agg.empty

  final override def testScalacPluginIvyDeps = scalacPluginIvyDeps

  final override def jvmTestFrameworks = Seq("org.scalatest.tools.Framework")

  final override def jsTestFrameworks = jvmTestFrameworks

  def alirObject: CrossJvmJsPublish

  def transpilersCObject: CrossJvmJsPublish

  def toolsObject: CrossJvmJsPublish

  def logikaObject: CrossJvmJsPublish
  
  def hamrCodegenObject: CrossJvmJsPublish

  def phantomObject: JvmPublish

  def proyekObject: JvmPublish

  def testObject: CrossJvmJsPublish
}
