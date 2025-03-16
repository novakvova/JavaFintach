import { useGoogleLogin } from '@react-oauth/google';
import { Button } from 'antd';
import {LoginButtonProps} from "./types.ts";

const GoogleLoginButton: React.FC<LoginButtonProps> = ({ onLogin, title, icon }) => {

    //Викликаємо AccessToken для доступу до інформації
    const login = useGoogleLogin({
        onSuccess: async (authCodeResponse) => {
            console.log("Login Google Token", authCodeResponse.access_token);
            onLogin(authCodeResponse.access_token);
            // const result = await accountService.googleLogin({ token: authCodeResponse.access_token });
            // if (result.status === 200) {
            //     console.log(result.data)
            //     onLogin(result.data.token)
            // }

        },
        onError: (error) => {
            console.error('Login Failed:', error);
        },
    });

    return (
        <Button icon={icon} onClick={()=>login()}>
            {title}
        </Button>
    );
};

export default GoogleLoginButton;