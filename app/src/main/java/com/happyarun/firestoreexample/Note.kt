package com.happyarun.firestoreexample

class Note {

    var title:String?=null
    var description:String?=null
    var priority:Long?=null
    constructor() {}
    constructor(title: String?, description: String?, priority: Long?) {
        this.title = title
        this.description = description
        this.priority = priority
    }


}