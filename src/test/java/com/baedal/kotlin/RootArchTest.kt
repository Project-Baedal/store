package com.baedal.kotlin

import com.tngtech.archunit.core.domain.JavaClasses
import com.tngtech.archunit.core.importer.ImportOption
import com.tngtech.archunit.junit.AnalyzeClasses
import com.tngtech.archunit.junit.ArchTest
import com.tngtech.archunit.library.Architectures

@AnalyzeClasses(
    packages = ["com.baedal.store"],
    importOptions = [ImportOption.DoNotIncludeTests::class]
)
class RootArchTest {

    @ArchTest
    fun no_dependency_on_java(classes: JavaClasses) {
        Architectures.layeredArchitecture()
            .consideringAllDependencies()
            .layer("java").definedBy("com.baedal.store.java..")
            .layer("kotlin").definedBy("com.baedal.store.kotlin..")
            .whereLayer("java").mayOnlyBeAccessedByLayers("java")
            .whereLayer("kotlin").mayOnlyBeAccessedByLayers("kotlin")
            .check(classes);
    }
}
