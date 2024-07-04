CREATE TABLE IF NOT EXISTS student_score (
    `id` BIGINT NOT NULL COMMENT '인조키' AUTO_INCREMENT,
    `exam` VARCHAR(255) NOT NULL COMMENT '시험',
    `student_name` VARCHAR(255) NOT NULL COMMENT '학생 이름',
    `kor_score` INT NOT NULL COMMENT '국어 시험 점수',
    `english_score` INT NOT NULL COMMENT '영어 시험 점수',
    `math_score` INT NOT NULL COMMENT '수학 시험 점수',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS passed_exam (
    `id` BIGINT NOT NULL COMMENT '인조키' AUTO_INCREMENT,
    `exam` VARCHAR(255) NOT NULL COMMENT '시험',
    `student_name` VARCHAR(255) NOT NULL COMMENT '학생 이름',
    `avg_score` DOUBLE NOT NULL COMMENT '평균 시험 점수',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS failed_exam (
    `id` BIGINT NOT NULL COMMENT '인조키' AUTO_INCREMENT,
    `exam` VARCHAR(255) NOT NULL COMMENT '시험',
    `student_name` VARCHAR(255) NOT NULL COMMENT '학생 이름',
    `avg_score` DOUBLE NOT NULL COMMENT '평균 시험 점수',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;