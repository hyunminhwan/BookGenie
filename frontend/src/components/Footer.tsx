import styles from "./Footer.module.css"; // CSS 모듈 가져오기

export default function Footer() {
  return (
    <footer className={styles.footer}>
      <div className={styles.footerContainer}>
        <div className={styles.footerInfo}>
          <p>프로젝트명 : <strong>MovieGenie</strong></p>
          <p>핸드폰 번호 : <strong>010-3550-3319</strong></p>
          <p>개인프로젝트 : <strong>현민환</strong></p>
        </div>
        <div className={styles.footerLinks}>
          <p>
            GitHub :{" "}
            <a
              href="https://github.com/hyunminhwan"
              target="_blank"
              rel="noopener noreferrer"
              className={styles.link}
            >
              hyunminhwan
            </a>
          </p>
        </div>
      </div>
    </footer>
  );
}
