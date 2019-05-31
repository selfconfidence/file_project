var vue = new Vue({
    el: '#app',
    data: {
            deleteFileLocal:'',
    },
    methods:{
        deleteFile(){
            this.$confirm('此操作将永久删除该路径文件, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {

                //删除逻辑操作
                axios.post('/file/deleteFiles',{"fileLocal":this.deleteFileLocal}).then( response => {
                    alert(response.data)
                    if (response.data.flag) {
                        this.messageTitle("success",response.data.message);
                        this.$message({
                            type: 'success',
                            message: '删除成功!'
                        });
                    }else {
                        this.messageTitle("error",response.data.message);
                        this.$message({
                            type: 'error',
                            message: '删除失败!'
                        });
                    }

                })

            }).catch((e) => {
              alert(e)
                this.$message({
                    type: 'info',
                    message: '已取消删除'
                });
            });
        },
       messageTitle(type,message){
           const h = this.$createElement;
           this.$notify({
               title: '删除提示!',
               message: h('i', { style: 'color: red'}, message),
               type: type
           });
       }
    }
})