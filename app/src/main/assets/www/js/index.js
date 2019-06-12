/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
var app = {
    // Application Constructor
    initialize: function() {
        document.addEventListener('deviceready', this.onDeviceReady.bind(this), false);
    },

    // deviceready Event Handler
    //
    // Bind any cordova events here. Common events are:
    // 'pause', 'resume', etc.
    onDeviceReady: function() {
        this.receivedEvent('deviceready');
    },

    // Update DOM on a Received Event
    receivedEvent: function(id) {
        var parentElement = document.getElementById(id);
        var listeningElement = parentElement.querySelector('.listening');
        var receivedElement = parentElement.querySelector('.received');

        listeningElement.setAttribute('style', 'display:none;');
        receivedElement.setAttribute('style', 'display:block;');

        console.log('Received Event: ' + id);
    }
};

app.initialize();

document.getElementById("setLocalStorage").addEventListener("click", setLocalStorage);
document.getElementById("showLocalStorage").addEventListener("click", showLocalStorage);
document.getElementById("removeProjectFromLocalStorage").addEventListener
   ("click", removeProjectFromLocalStorage);
document.getElementById("getLocalStorageByKey").addEventListener
   ("click", getLocalStorageByKey);

var localStorage = window.localStorage;

function setLocalStorage() {
   localStorage.setItem("Name", "John");
   localStorage.setItem("Job", "Developer");
   localStorage.setItem("Project", "Cordova Project");
}
function showLocalStorage() {
   console.log(localStorage.getItem("Name"));
   console.log(localStorage.getItem("Job"));
   console.log(localStorage.getItem("Project"));
}
function removeProjectFromLocalStorage() {
    localStorage.removeItem("Project");
}

function getLocalStorageByKey() {
    console.log(localStorage.key(0));
}

//document.addEventListener("backbutton", onBackKeyDown, false);
//
//function onBackKeyDown(e) {
//   e.preventDefault();
//   alert('Back Button is Pressed!');
//}

document.getElementById("cameraTakePicture").addEventListener("click", cameraTakePicture);

function cameraTakePicture() {
   navigator.camera.getPicture(onSuccess, onFail, {
      quality: 50,
      destinationType: Camera.DestinationType.DATA_URL
   });

   function onSuccess(imageData) {
      var image = document.getElementById('myImage');
      image.src = "data:image/jpeg;base64," + imageData;
   }

   function onFail(message) {
      alert('Failed because: ' + message);
   }
}

document.getElementById("speekButton").addEventListener("click", speekTest);

function speekTest(){
    cordova.exec(success, fail, "SpeechOFFSynthesize", "speak", ["haha"]);
}
var success = function(message){
    alert("success = "+message);
};
var fail = function(message){
    alert("fail = "+message);
};

function showAlert(content){
    alert(content);
}

document.getElementById("routerTest").addEventListener("click",routerTest);

function routerTest(){
    cordova.exec(success1, fail1, "SpeechOFFSynthesize", "router", ["/test/1","key1","key2"])
}
var success1 = function(message){
    alert("success = "+message);
};
var fail1 = function(message){
    alert("fail = "+message);
};