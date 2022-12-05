package shop.mtcoding.final5th.dto;

import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.final5th.domain.todo.Todo;

public class TodoRespDto {

    @Setter
    @Getter
    public static class TodoListRespDto {
        private List<TodoDto> todoDtos;

        public TodoListRespDto(List<Todo> todoList) {
            this.todoDtos = todoList.stream().map((todo) -> new TodoDto(todo))
                    .collect(Collectors.toList());
        }

        @Setter
        @Getter
        public class TodoDto {
            private Long todoId;
            private Long userId;
            private String todoTitle;
            private boolean todoFinished;

            public TodoDto(Todo todo) {
                this.todoId = todo.getTodoId();
                this.userId = todo.getUserId();
                this.todoTitle = todo.getTodoTitle();
                this.todoFinished = todo.isTodoFinished();
            }
        }
    }

    @Setter
    @Getter
    public static class TodoDetailRespDto {
        private Long todoId;
        private Long userId;
        private String todoTitle;
        private boolean todoFinished;

        public TodoDetailRespDto(Todo todo) {
            this.todoId = todo.getTodoId();
            this.userId = todo.getUserId();
            this.todoTitle = todo.getTodoTitle();
            this.todoFinished = todo.isTodoFinished();
        }
    }

    @Setter
    @Getter
    public static class TodoSaveRespDto {
        private Long todoId;
        private Long userId;
        private String todoTitle;
        private boolean todoFinished;

        public TodoSaveRespDto(Todo todo) {
            this.todoId = todo.getTodoId();
            this.userId = todo.getUserId();
            this.todoTitle = todo.getTodoTitle();
            this.todoFinished = todo.isTodoFinished();
        }
    }

    @Setter
    @Getter
    public static class TodoUpdateRespDto {
        private String todoTitle;
        private boolean todoFinished;

        public TodoUpdateRespDto(Todo todo) {
            this.todoTitle = todo.getTodoTitle();
            this.todoFinished = todo.isTodoFinished();
        }
    }
}
