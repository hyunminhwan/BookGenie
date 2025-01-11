"use client";

import "./globals.css";
import Header from "@/components/Header" ;
import Footer from "@/components/Footer";
import { Provider } from "react-redux";
import { store } from "@/store";
export default function RootLayout({children}: Readonly<{children: React.ReactNode}>) {
  //Provider,store 리덕스 설정
  return (
    <html lang="en">
      <body>
        <Provider store={store}>
          <Header />
            {children}
          <Footer />
        </Provider>
      </body>
    </html>
  );
}
