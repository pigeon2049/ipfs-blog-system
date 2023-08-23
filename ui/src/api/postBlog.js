import request from "./request.js";

export  function postBlog(content){
    return request.post("/post",content);
}