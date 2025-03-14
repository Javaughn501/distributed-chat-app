import './App.css'
import Dashboard from './modules/Dashboard'
import Form from './modules/Form'
import { Routes, Route, Navigate } from 'react-router-dom'

const ProtectedRoute = ({ children, auth = false }) => {
  const isLoggedIn = localStorage.getItem('user:token') !== null || false;

  if (!isLoggedIn && auth) {
    return <Navigate to={'/signin'} />
  }
  else if (isLoggedIn && ['signin', 'signup'].includes(window.location.pathname)) {
    return <Navigate to={'/'} />
  }

  return children
}

function App() {
  return (
    <Routes>
      <Route path="/" element={
        <ProtectedRoute auth={true}>
          <Dashboard />
        </ProtectedRoute>
      } />
      <Route path="/signin" element={
        <ProtectedRoute>
          <Form isSignInPage={true} />
        </ProtectedRoute>
      } />
      <Route path="/signup" element={
        <ProtectedRoute>
          <Form isSignInPage={false} />
        </ProtectedRoute>
      } />
    </Routes>
  )
}

export default App
