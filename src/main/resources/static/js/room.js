var vm = new Vue({
  el: "#app",
  data: {
    room_name: "",
    chatrooms: [],
  },
  created() {
    this.findAllRoom();
  },
  methods: {
    findAllRoom: function () {
      axios.get("/chat/rooms").then((response) => {
        this.chatrooms = response.data;
      });
    },
    createRoom: function () {
      if (!this.room_name) {
        alert("チャットルーム名を入力してください");
        return;
      } else {
        var params = new URLSearchParams();
        params.append("name", this.room_name);
        axios
          .post("/chat/room", params)
          .then((response) => {
            alert(response.data.name + "開設に成功しました");
            this.room_name = "";
            this.findAllRoom();
          })
          .catch((response) => {
            alert("チャットルーム開設に失敗しました");
          });
      }
    },
    enterRoom: function (roomId) {
      var sender = prompt("ニックネームを入力してください。");
      if (sender) {
        localStorage.setItem("wschat.sender", sender);
        localStorage.setItem("wschat.roomId", roomId);
        location.href = "/chat/room/enter/" + roomId;
      }
    },
  },
});
